import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String hosts = args[0];
            String nodePath = args[1];
            String executablePath = args[2];
            int timeOut = Integer.parseInt(args[3]);

            try {
                Thread applicationThread = new Thread(new Application(hosts, timeOut, nodePath,executablePath));
                applicationThread.start();
                applicationThread.join();
            } catch (IOException | InterruptedException e){
                e.printStackTrace();
            }

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
