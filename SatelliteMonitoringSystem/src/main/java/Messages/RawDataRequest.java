package Messages;

public record RawDataRequest(int firstSatelliteID, int range, int timeout) implements SatelliteDataGeneric {}
