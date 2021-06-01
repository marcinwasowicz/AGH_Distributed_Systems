package server;

import Office.Service;
import Office.basicCaseData;

import com.zeroc.Ice.Current;

import java.util.Random;
import java.util.concurrent.ExecutorService;

public class ServiceImpl implements Service {

    private final WorkerService workerService;
    private final ExecutorService workerServiceThreadPool;
    private final Random random;

    public ServiceImpl(WorkerService workerService, ExecutorService workerServiceThreadPool) {
        this.workerService = workerService;
        this.workerServiceThreadPool = workerServiceThreadPool;
        this.random = new Random();
    }

    @Override
    public int requestUnemployedAid(basicCaseData data, float averageEarnings, Current current) {
        int expectedTime = this.random.nextInt(6) + 5;
        this.workerServiceThreadPool.submit(() -> this.workerService.resolveUnemployedAid(
                data,
                expectedTime,
                averageEarnings
        ));
        return expectedTime;
    }

    @Override
    public int requestBuildingPermission(basicCaseData data, String address, float area, Current current) {
        int expectedTime = this.random.nextInt(6) + 5;
        this.workerServiceThreadPool.submit(() -> this.workerService.resolveBuildingPermission(
                data,
                expectedTime,
                address,
                area
        ));
        return expectedTime;
    }

    @Override
    public int requestDrivingLicence(basicCaseData data, long candidateProfile, boolean isFirstLicence, Current current) {
        int expectedTime = this.random.nextInt(6) + 5;
        this.workerServiceThreadPool.submit(() -> this.workerService.resolveDrivingLicence(
                data,
                expectedTime,
                candidateProfile,
                isFirstLicence
        ));
        return expectedTime;
    }
}
