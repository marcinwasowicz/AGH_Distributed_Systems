package server;

import Notification.NotifierPrx;
import Office.Reception;
import com.zeroc.Ice.Current;

import java.util.concurrent.ExecutorService;


public class ReceptionImpl implements Reception {

    private final WorkerService workerService;
    private final ExecutorService workerServiceThreadPool;

    public ReceptionImpl(WorkerService workerService, ExecutorService workerServiceThreadPool) {
        this.workerService = workerService;
        this.workerServiceThreadPool = workerServiceThreadPool;
    }

    @Override
    public void register(int uniqueID, NotifierPrx notifier, Current current) {
        this.workerService.updateProxy(uniqueID, notifier.ice_fixed(current.con));
        this.workerServiceThreadPool.submit(
                () -> this.workerService.resolveCachedCases(uniqueID)
        );
    }
}
