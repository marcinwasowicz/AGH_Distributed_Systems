package randomStreamingService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

public class RandomStreamerServer {
    private final Server server;

    public RandomStreamerServer(int port){
        this.server = ServerBuilder.
                forPort(port).
                executor(Executors.newCachedThreadPool()).
                addService(new RandomStreamerImpl()).
                build();
        Runtime.getRuntime().addShutdownHook(new Thread(RandomStreamerServer.this::stop));
    }

    private void start() throws IOException {
        this.server.start();
    }

    private void stop(){
        if(this.server != null){
            this.server.shutdown();
        }
    }

    private void waitForShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomStreamerServer server = new RandomStreamerServer(Integer.parseInt(args[0]));
        server.start();
        server.waitForShutdown();
    }
}
