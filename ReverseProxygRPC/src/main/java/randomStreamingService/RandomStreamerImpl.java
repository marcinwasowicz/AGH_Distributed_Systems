package randomStreamingService;

import io.grpc.stub.StreamObserver;
import randomStreaming.NextRandom;
import randomStreaming.RandomStreamRequest;
import randomStreaming.RandomStreamerGrpc;

import java.util.Random;

public class RandomStreamerImpl extends RandomStreamerGrpc.RandomStreamerImplBase {

    @Override
    public StreamObserver<RandomStreamRequest> getRandomStream(StreamObserver<NextRandom> observer){
        return new StreamObserver<>() {
            private final Random generator = new Random();

            @Override
            public void onNext(RandomStreamRequest request) {
                System.out.println("Started random numbers stream");
                for(int i = 0; i < request.getCount(); i++){
                    try {
                        Thread.sleep(request.getTimeInterval());
                        observer.onNext(NextRandom.newBuilder().setNext(generator.nextInt(request.getBound())).build());
                    } catch (Exception e){
                        observer.onError(e);
                        this.onError(e);
                        return;
                    }
                }
                observer.onCompleted();
                this.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error occurred while streaming: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Streaming completed successfully");
            }
        };
    }
}
