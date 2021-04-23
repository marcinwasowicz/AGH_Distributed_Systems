package Messages;

import Satellite.SatelliteAPI;
import java.util.HashMap;

public record SatelliteDatabaseUpdate(HashMap<Integer, SatelliteAPI.Status> errors) implements SatelliteDataGeneric {}
