package Actors;

import Messages.*;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.HashMap;

public class MonitoringStation extends AbstractBehavior<Messages.SatelliteDataGeneric> {

    private final String stationName;
    private final ActorRef<SatelliteDataGeneric> dispatcher;
    private final HashMap<Integer, Long> queryInitTimes;
    private int autoID;

    public MonitoringStation(ActorContext<SatelliteDataGeneric> context, String stationName, ActorRef<SatelliteDataGeneric> dispatcher) {
        super(context);
        this.dispatcher = dispatcher;
        this.stationName = stationName;
        this.autoID = -1;
        this.queryInitTimes = new HashMap<>();
    }

    public static Behavior<SatelliteDataGeneric> createStation(String stationName, ActorRef<SatelliteDataGeneric> dispatcher){
        return Behaviors.setup(context -> new MonitoringStation(context, stationName, dispatcher).createReceive());
    }

    @Override
    public Receive<SatelliteDataGeneric> createReceive() {
        return newReceiveBuilder().
                onMessage(RawDataRequest.class, this::onRawDataRequest).
                onMessage(RawSatelliteDatabaseQuery.class, this::onRawSatelliteDatabaseQuery).
                onMessage(SatelliteDataResponse.class, this::onSatelliteDataResponse).
                onMessage(SatelliteDatabaseResponse.class, this::onSatelliteDatabaseResponse).
                build();
    }

    private Behavior<SatelliteDataGeneric> onRawDataRequest(RawDataRequest msg){
        int newQueryID = this.getAutoID();
        this.dispatcher.tell(new SatelliteDataRequest(
                newQueryID,
                msg.firstSatelliteID(),
                msg.range(),
                msg.timeout(),
                getContext().getSelf()
        ));
        this.queryInitTimes.put(newQueryID, System.currentTimeMillis());
        return this;
    }

    private Behavior<SatelliteDataGeneric> onRawSatelliteDatabaseQuery(RawSatelliteDatabaseQuery msg){
        this.dispatcher.tell(new SatelliteDatabaseQuery(getContext().getSelf(), msg.satelliteID()));
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDataResponse(SatelliteDataResponse msg){
        long timeElapsed = System.currentTimeMillis() - this.queryInitTimes.get(msg.queryID());
        System.out.println(
                "STATION: " + stationName + "\n" + msg +
                        "RECEIVED AFTER (ms): " + timeElapsed + "\nRESPONDED IN TIME: "
                        + msg.respondedInTime()
        );
        this.queryInitTimes.remove(msg.queryID());
        return this;
    }

    private Behavior<SatelliteDataGeneric> onSatelliteDatabaseResponse(SatelliteDatabaseResponse msg){
        System.out.println(
                "SATELLITE OF ID " + msg.satelliteID() + " RETURNED " + msg.errorCount() + " ERRORS IN TOTAL"
        );
        return this;
    }

    private int getAutoID(){
        this.autoID++;
        return this.autoID;
    }
}
