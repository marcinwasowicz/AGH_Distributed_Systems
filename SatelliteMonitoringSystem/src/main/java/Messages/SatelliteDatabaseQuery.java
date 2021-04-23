package Messages;

import akka.actor.typed.ActorRef;

public record SatelliteDatabaseQuery(ActorRef<SatelliteDataGeneric> station, int satelliteID) implements SatelliteDataGeneric {}
