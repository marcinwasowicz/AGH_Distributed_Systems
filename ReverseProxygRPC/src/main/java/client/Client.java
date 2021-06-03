package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import letterFormatting.LetterFormatRequest;
import letterFormatting.LetterFormatterGrpc;
import randomStreaming.RandomStreamRequest;
import randomStreaming.RandomStreamerGrpc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {

    private final ManagedChannel channel;
    private final RandomStreamerGrpc.RandomStreamerStub streamerStub;
    private final LetterFormatterGrpc.LetterFormatterBlockingStub formatterBlockingStub;

    private final Scanner scanner;
    private boolean running;

    public Client(String proxyAddress, int proxyPort){
        this.channel = ManagedChannelBuilder.forAddress(proxyAddress, proxyPort).usePlaintext().build();
        this.streamerStub = RandomStreamerGrpc.newStub(channel);
        this.formatterBlockingStub = LetterFormatterGrpc.newBlockingStub(this.channel);
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void runApp() throws InterruptedException {
        System.out.println("Streaming command format: stream <bound> <count> <time interval>\n" +
                "Letter formatting command format: format <is upper case> <text to format>");

        while(this.running) {
            String[] line = this.scanner.nextLine().strip().split(" ");
            if(line.length == 4 && line[0].equals("stream")) {
                try{
                    RandomStreamRequest request = RandomStreamRequest.newBuilder().
                            setBound(Integer.parseInt(line[1])).
                            setCount(Integer.parseInt(line[2])).
                            setTimeInterval(Integer.parseInt(line[3])).
                            build();
                    this.streamerStub.getRandomStream(new RandomStreamObserver()).onNext(request);

                } catch (NumberFormatException e){
                    System.out.println("stream command arguments must be integers!");
                }
            }
            else if(line.length == 3 && line[0].equals("format")){
                String formatted = this.formatterBlockingStub.formatLetters(
                        LetterFormatRequest.newBuilder().
                                setText(line[2]).
                                setToUpper(Boolean.parseBoolean(line[1])).
                                build()
                ).getText();
                System.out.println("Formatting result: " + formatted);
            }
            else if(line.length == 1 && line[0].equals("quit")){
                this.running = false;
            }
            else{
                System.out.println("Invalid arguments or unrecognized command provided.");
            }
        }
        this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.runApp();
    }
}
