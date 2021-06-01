package server;

import com.mongodb.MongoClient;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        try {
            MongoClient databaseConnection = new MongoClient();

            Communicator communicator = Util.initialize(args);
            ObjectAdapter office = communicator.createObjectAdapter("Office");

            ExecutorService workerServiceThreadPoll = Executors.newCachedThreadPool();
            WorkerService workerService = new WorkerService(databaseConnection);

            ReceptionImpl reception = new ReceptionImpl(workerService, workerServiceThreadPoll);
            ServiceImpl service = new ServiceImpl(workerService, workerServiceThreadPoll);

            office.add(reception, Util.stringToIdentity("Reception"));
            office.add(service, Util.stringToIdentity("Service"));

            office.activate();
            communicator.waitForShutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
