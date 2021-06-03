package client;

import io.grpc.stub.StreamObserver;
import randomStreaming.NextRandom;

public class RandomStreamObserver implements StreamObserver<NextRandom> {

    private int count;

    public RandomStreamObserver() {
        this.count = 0;
    }

    @Override
    public void onNext(NextRandom nextRandom) {
        System.out.println(this.count + "-th value: " + nextRandom.getNext());
        this.count++;
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Stream unexpectedly closed due to the following error: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Random streaming completed successfully");
    }
}
