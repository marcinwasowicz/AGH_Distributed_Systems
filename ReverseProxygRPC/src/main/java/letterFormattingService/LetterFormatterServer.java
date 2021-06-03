package letterFormattingService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

public class LetterFormatterServer {
    private final Server server;

    public LetterFormatterServer(int port){
        this.server = ServerBuilder.
                forPort(port).
                executor(Executors.newCachedThreadPool()).
                addService(new LetterFormatterImpl()).
                build();
        Runtime.getRuntime().addShutdownHook(new Thread(LetterFormatterServer.this::stop));
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
        LetterFormatterServer server = new LetterFormatterServer(Integer.parseInt(args[0]));
        server.start();
        server.waitForShutdown();
    }
}
