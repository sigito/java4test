package thread.interruption;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author sigito
 */
public class SocketInterruption {
    public static final String HOST = "localhost";
    public static final int PORT = 10500;

    static class Server extends Thread {

        private ServerSocket serverSocket;
        private CountDownLatch latch;

        Server(CountDownLatch latch) {
            super("Server");
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(PORT);
                // connect and don't do anything
                Socket socket = serverSocket.accept();
                latch.await();
                socket.close();
            } catch (IOException | InterruptedException e) {
                System.out.println("Server error: " + e.getMessage());
            }
        }
    }

    static class Client extends Thread {
        private Socket socket;

        Client() {
            super("Client");
        }

        @Override
        public void run() {
            try {
                socket = new Socket(HOST, PORT);
                int readByte = socket.getInputStream().read();
                System.out.println("Read byte: " + readByte);
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            }
        }
    }

    // TODO Add more description
    // interruption of thread on socket read
    public static void test() {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            Server server = new Server(latch);
            server.start();
            Client client = new Client();
            client.start();

            System.out.println("Waiting threads start.");
            Thread.sleep(5000);

            client.interrupt();

            System.out.println("Waiting client interruption.");
            Thread.sleep(5000);

            latch.countDown();

            server.join();
            client.join();
        } catch (InterruptedException e) {
            System.out.println("Error joining: " + e.getMessage());
        }
    }
}
