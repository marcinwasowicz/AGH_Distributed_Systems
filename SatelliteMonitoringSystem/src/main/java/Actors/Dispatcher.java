package Actors;

import DataHolders.QueryDictionary;
import Messages.*;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.DispatcherSelector;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.mongodb.MongoClient;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher extends AbstractBehavior<SatelliteDataGeneric> {

    private final ArrayList<ActorRef<SatelliteDataGeneric>> workers;
    private final QueryDictionary queryDictionary;
    private final Random random;

    public Dispatcher(ActorContext<SatelliteDataGeneric> context, int numberOfWorkers, MongoClient databaseConnection) {
        super(context);
        this.workers = new ArrayList<>();
        this.queryDictionary = new QueryDictionary();
        this.random = new Random();
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for(int i = 0; i<numberOfWorkers; i++){
            this.workers.add(getContext().spawn(
                    Behaviors.supervise(Worker.createWorker(getContext().getSelf(), databaseConnection, threadPool)).
                            onFailure(Exception.class, SupervisorStrategy.restart()), "worker" + i,
                    DispatcherSelector.fromConfig("my-dispatcher")));
        }
    }

    public static Behavior<SatelliteDataGeneric> createDispatcher(int numberOfWorkers, MongoClient databaseConnection){
        return Behaviors.setup(context -> new Dispatcher(context, numberOfWorkers, databaseConnection).createReceive());
    }

    @Override
    public Receive<SatelliteDataGeneric> createReceive() {
        return newReceiveBuilder().
                onMessage(SatelliteDataRequest.class, this::onSatelliteDataRequest).
                onMessage(SingleSatelliteStatusResponse.class, this::onSingleSatelliteStatusResponse).
                onMessage(SatelliteDatabaseQuery.class, this::onSatelliteDatabaseQuery).
                onMessage(SatelliteDatabaseResponse.class, this::onSatelliteDatabaseResponse).
                build();
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDataRequest(SatelliteDataRequest msg){
        this.queryDictionary.initQuery(msg);
        for(int i = 0; i< msg.range(); i++){
            this.getWorker().tell(new SingleSatelliteStatusRequest(
                    msg.station(),
                    msg.queryID(),
                    msg.firstSatelliteID() + i,
                    msg.timeout()
            ));
        }
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSingleSatelliteStatusResponse(SingleSatelliteStatusResponse msg){
        if(this.queryDictionary.isQueryActive(msg)) {
            this.queryDictionary.updateQuery(msg);
            if (this.queryDictionary.isQueryCompleted(msg)) {
                SatelliteDataResponse response = this.queryDictionary.formulateResponse(msg);
                msg.station().tell(response);
                this.getWorker().tell(new SatelliteDatabaseUpdate(response.errors()));
            }
        }
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDatabaseQuery(SatelliteDatabaseQuery msg){
        this.getWorker().tell(msg);
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDatabaseResponse(SatelliteDatabaseResponse msg){
        msg.station().tell(msg);
        return this;
    }

    private ActorRef<SatelliteDataGeneric> getWorker(){
        return this.workers.get(this.random.nextInt(this.workers.size()));
    }
}
