package Messages;

import akka.actor.typed.ActorRef;

public record SatelliteDataRequest(int queryID, int firstSatelliteID, int range, int timeout, ActorRef<SatelliteDataGeneric> station) implements SatelliteDataGeneric {

    @Override
    public String toString() {
        return "SatelliteDataRequest{" +
                "queryID=" + queryID +
                ", firstSatelliteID=" + firstSatelliteID +
                ", range=" + range +
                ", timeout=" + timeout +
                ", station=" + station +
                '}';
    }
}
