package sprint_4.contactList;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public Server() {
        try (ServerSocket socket = new ServerSocket(5000)) {
            while (true) {
                new ConnectionHandler(socket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Databas.load();
        Server server = new Server();
    }
}
