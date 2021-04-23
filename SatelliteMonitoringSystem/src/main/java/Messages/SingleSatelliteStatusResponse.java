package Messages;

import Satellite.SatelliteAPI;
import akka.actor.typed.ActorRef;


public record SingleSatelliteStatusResponse(ActorRef<SatelliteDataGeneric> station, int queryID, int satelliteID, SatelliteAPI.Status replyCode) implements SatelliteDataGeneric {
    @Override
    public String toString() {
        return "SingleSatelliteStatusResponse{" +
                "station=" + station +
                ", queryID=" + queryID +
                ", satelliteID=" + satelliteID +
                ", replyCode=" + replyCode +
                '}';
    }
}
