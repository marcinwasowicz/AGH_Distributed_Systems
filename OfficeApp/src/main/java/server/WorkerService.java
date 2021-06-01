package server;

import Notification.NotifierPrx;
import Notification.caseResult;
import Notification.timeStamp;
import Office.basicCaseData;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class WorkerService {
    private final MongoClient databaseConnection;
    private final ConcurrentHashMap<Integer, NotifierPrx> userProxies;

    public WorkerService(MongoClient databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.userProxies = new ConcurrentHashMap<>();
    }

    public void updateProxy(int userUniqueId, NotifierPrx userProxy){
        this.userProxies.put(userUniqueId, userProxy);
    }

    public void resolveCachedCases(int uniqueID) {
        var resolvedCases = this.databaseConnection.getDatabase("Office").
                getCollection("Cases").find(
                Filters.eq("unique_id", uniqueID)
        );
        ArrayList<caseResult> resultsBatch = new ArrayList<>();
        for(var result: resolvedCases) {
            resultsBatch.add(
                    new caseResult(
                            new timeStamp(
                                    result.getInteger("hour"),
                                    result.getInteger("minute"),
                                    result.getInteger("second")
                            ),
                            result.getInteger("expectedResolutionTime"),
                            result.getString("result")
                    )
            );
        }
        if(resultsBatch.size() == 0){
            return;
        }
        try{
            this.userProxies.get(uniqueID).batchedNotify(resultsBatch.toArray(new caseResult[0]));
        }
        catch (Exception e) {
            return;
        }
        this.databaseConnection.
                getDatabase("Office").
                getCollection("Cases").
                deleteMany(Filters.eq("unique_id", uniqueID));
    }

    public void resolveUnemployedAid(basicCaseData data, int expectedTime, float averageEarnings) {
        this.simulateWork(expectedTime);
        String resultMessage = averageEarnings > 1500.0f ? "Average earnings to high to grant aid" : "Aid granted successfully";
        this.trySendResults(data, expectedTime, "aid", resultMessage);
    }

    public void resolveBuildingPermission(basicCaseData data, int expectedTime, String address, float area) {
        this.simulateWork(expectedTime);
        String resultMessage = area > 2000.0f ? "Area at address " + address + " is to high to grant permission" :
                "Permission granted successfully";
        this.trySendResults(data, expectedTime, "permission", resultMessage);
    }

    public void resolveDrivingLicence(basicCaseData data, int expectedTime, long candidateProfile, boolean isFirstLicence) {
        this.simulateWork(expectedTime);
        String resultMessage = isFirstLicence ? "Licence granted successfully" :
                "Could not grant driving licence for candidate number: " + candidateProfile;
        this.trySendResults(data, expectedTime, "licence", resultMessage);
    }

    private void dumpToDatabase(basicCaseData data, int expectedTime, String type, String result){
        this.databaseConnection.getDatabase("Office").getCollection("Cases").
                insertOne(new Document().
                        append("unique_id", data.uniqueID).
                        append("hour", data.registrationTime.hour).
                        append("minute", data.registrationTime.minute).
                        append("second", data.registrationTime.second).
                        append("expectedResolutionTime", expectedTime).
                        append("type", type).
                        append("resolved", true).
                        append("result", result));
    }

    private void trySendResults(basicCaseData data, int expectedTime, String type, String result){
        try {
            this.userProxies.get(data.uniqueID).singleNotify(
                    new caseResult(data.registrationTime, expectedTime, result)
            );
        } catch(Exception e){
            this.dumpToDatabase(data, expectedTime, type, result);
        }
    }

    private void simulateWork(int expectedTime){
        try {
            Thread.sleep(expectedTime * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
