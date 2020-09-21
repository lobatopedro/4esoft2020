package forumSocketsAndThread;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServerPingPong {

    private static final int PORT = 8080;

    public static void main(String[] args) {

        final SocketServerPingPong server = new SocketServerPingPong();
        server.clientListener();
    }

    private void clientListener() {

        try (ServerSocket socket = new ServerSocket(PORT)) {

            while (true) {
                SocketServerPingPong client = new SocketServerPingPong();
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
    }
}
