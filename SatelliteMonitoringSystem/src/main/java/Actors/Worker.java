package Actors;

import Messages.*;
import Satellite.SatelliteAPI;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class Worker extends AbstractBehavior<SatelliteDataGeneric> {

    private final ActorRef<SatelliteDataGeneric> dispatcher;
    private final MongoCollection<Document> database;
    private final ExecutorService threadPool;

    public static Behavior<SatelliteDataGeneric> createWorker(ActorRef<SatelliteDataGeneric> dispatcher, MongoClient databaseConnection, ExecutorService threadPool){
        return Behaviors.setup(context -> new Worker(context, dispatcher, databaseConnection, threadPool).createReceive());
    }

    public Worker(ActorContext<SatelliteDataGeneric> context, ActorRef<SatelliteDataGeneric> dispatcher, MongoClient databaseConnection, ExecutorService threadPool) {
        super(context);
        this.dispatcher = dispatcher;
        this.database = databaseConnection.getDatabase("SatelliteDatabase").getCollection("SatelliteErrors");
        this.threadPool = threadPool;
    }

    @Override
    public Receive<SatelliteDataGeneric> createReceive() {
        return newReceiveBuilder().
                onMessage(SatelliteDatabaseUpdate.class, this::onSatelliteDatabaseUpdate).
                onMessage(SatelliteDatabaseQuery.class, this::onSatelliteDatabaseQuery).
                onMessage(SingleSatelliteStatusRequest.class, this::onSingleSatelliteStatusRequest).
                build();
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDatabaseUpdate(SatelliteDatabaseUpdate msg){
        this.threadPool.submit(() ->
        this.database.updateMany(
                Filters.in("_id", msg.errors().keySet()),
                Updates.inc("ErrorCount", 1)
        ));
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDatabaseQuery(SatelliteDatabaseQuery msg){
        Document satelliteData = this.database.
                find(Filters.eq("_id", msg.satelliteID())).first();

        this.dispatcher.tell(new SatelliteDatabaseResponse(
                msg.station(),
                msg.satelliteID(),
                (int) Objects.requireNonNull(satelliteData).get("ErrorCount")
        ));

        return this;
    }

    private Behavior<SatelliteDataGeneric> onSingleSatelliteStatusRequest(SingleSatelliteStatusRequest msg){
        SingleSatelliteStatusResponse timeoutResponse = new SingleSatelliteStatusResponse(
                msg.station(),
                msg.queryID(),
                msg.satelliteID(),
                null
        );
        ActorSystem<Void> workerSystem = getContext().getSystem();
        workerSystem.scheduler().scheduleOnce(Duration.ofMillis(msg.timeout()),
                () -> this.dispatcher.tell(timeoutResponse),
                workerSystem.executionContext());

        this.threadPool.submit(() -> {
            SatelliteAPI.Status satelliteStatus = SatelliteAPI.getStatus(msg.satelliteID());
            SingleSatelliteStatusResponse statusResponse = new SingleSatelliteStatusResponse(
                    msg.station(),
                    msg.queryID(),
                    msg.satelliteID(),
                    satelliteStatus
            );
            this.dispatcher.tell(statusResponse);
        });

        return this;
    }
}
