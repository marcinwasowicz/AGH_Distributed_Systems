package Messages;

import akka.actor.typed.ActorRef;

public record SingleSatelliteStatusRequest(ActorRef<SatelliteDataGeneric> station, int queryID, int satelliteID, int timeout) implements SatelliteDataGeneric {}
