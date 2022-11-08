package sprint_4.introTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void startConnection(String ip, int port) {
        try (Socket clientSocket = new Socket(ip, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.startConnection(InetAddress.getLocalHost().getHostAddress(), 6666);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
