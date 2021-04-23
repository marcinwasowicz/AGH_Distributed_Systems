package Messages;

import Satellite.SatelliteAPI;
import java.util.HashMap;
import java.util.Map;

public record SatelliteDataResponse(int queryID, int respondedInTime, HashMap<Integer, SatelliteAPI.Status> errors) implements SatelliteDataGeneric {

    @Override
    public String toString() {
        StringBuilder listing = new StringBuilder("TOTAL ERROR COUNT " + this.errors.size() + "\nERROR LIST:\n");
        for (Map.Entry<Integer, SatelliteAPI.Status> idErrorPair : this.errors.entrySet()) {
            listing.append("SATELLITE ID: ").
                    append(idErrorPair.getKey()).
                    append(" ERROR CODE: ").
                    append(idErrorPair.getValue()).
                    append("\n");
        }
        return listing.toString();
    }
}
