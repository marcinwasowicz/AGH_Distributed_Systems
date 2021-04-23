package DataHolders;

import Messages.SatelliteDataGeneric;
import Messages.SatelliteDataRequest;
import Messages.SatelliteDataResponse;
import Messages.SingleSatelliteStatusResponse;
import akka.actor.typed.ActorRef;

import java.util.HashMap;

public class QueryDictionary {
    private final HashMap<ActorRef<SatelliteDataGeneric>, HashMap<Integer, QueryData>> queries;

    public QueryDictionary(){
        this.queries = new HashMap<>();
    }

    public void initQuery(SatelliteDataRequest request){
        if(!this.queries.containsKey(request.station())){
            this.queries.put(request.station(), new HashMap<>());
        }
        this.queries.get(request.station()).put(request.queryID(), new QueryData(request.range(), request.firstSatelliteID()));
    }

    public void updateQuery(SingleSatelliteStatusResponse response){
        QueryData queryData = this.queries.get(response.station()).get(response.queryID());
        if(queryData.satelliteRecorded(response.satelliteID())){
            return;
        }
        queryData.addResponse(response.satelliteID(), response.replyCode());
    }

    public boolean isQueryCompleted(SingleSatelliteStatusResponse response){
        return this.queries.get(response.station()).get(response.queryID()).isCompleted();
    }

    public boolean isQueryActive(SingleSatelliteStatusResponse response){
        if(!this.queries.containsKey(response.station())){
            return false;
        }
        return this.queries.get(response.station()).containsKey(response.queryID());
    }

    public SatelliteDataResponse formulateResponse(SingleSatelliteStatusResponse response){
        HashMap<Integer, QueryData> stationQueries = this.queries.get(response.station());
        QueryData queryData = stationQueries.get(response.queryID());
        stationQueries.remove(response.queryID());
        if(stationQueries.size() == 0){
            this.queries.remove(response.station());
        }

        return new SatelliteDataResponse(
                response.queryID(),
                queryData.getRespondedInTime(),
                queryData.formulateResponse()
        );
    }
}
