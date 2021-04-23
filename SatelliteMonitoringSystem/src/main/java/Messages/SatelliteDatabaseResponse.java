package Messages;

import akka.actor.typed.ActorRef;

public record SatelliteDatabaseResponse(ActorRef<SatelliteDataGeneric> station, int satelliteID, int errorCount) implements SatelliteDataGeneric {}
