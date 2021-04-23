package DataHolders;

import Satellite.SatelliteAPI;

import java.util.HashMap;

public class QueryData {
    private int responseCount;
    private int respondedInTime;
    private final int range;
    private final int firstSatelliteID;
    private final boolean[] recorded;
    private final SatelliteAPI.Status[] responses;

    public QueryData(int range, int firstSatelliteID){
        this.responseCount = 0;
        this.respondedInTime = 0;
        this.firstSatelliteID = firstSatelliteID;
        this.range = range;
        this.recorded = new boolean[range];
        this.responses = new SatelliteAPI.Status[range];
    }

    public boolean satelliteRecorded(int satelliteID){
        return this.recorded[satelliteID - this.firstSatelliteID];
    }

    public void addResponse(int satelliteID, SatelliteAPI.Status response){
        this.responseCount++;
        if(response != null){
            this.respondedInTime++;
        }
        this.recorded[satelliteID - firstSatelliteID] = true;
        this.responses[satelliteID - firstSatelliteID] = response;
    }

    public boolean isCompleted(){
        return this.responseCount == this.range;
    }

    public HashMap<Integer, SatelliteAPI.Status> formulateResponse(){
        HashMap<Integer, SatelliteAPI.Status> queryResponse = new HashMap<>();
        for(int i = 0; i<this.range; i++){
            if(this.responses[i] != null && this.responses[i] != SatelliteAPI.Status.OK){
                queryResponse.put(i + this.firstSatelliteID, this.responses[i]);
            }
        }
        return queryResponse;
    }

    public int getRespondedInTime() {
        return respondedInTime;
    }
}
