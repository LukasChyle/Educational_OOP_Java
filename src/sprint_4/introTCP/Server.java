package sprint_4.introTCP;

import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server implements Runnable {

    private final int port;
    private final int timer;

    public Server(int port, int timerSeconds) {
        this.port = port;
        timer = timerSeconds;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            while (true) {
                String timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                out.println("Time from the server: " + timeNow);
                System.out.println("New time was sent to client: " + timeNow);

                out.println("Time update in:");
                for (int i = timer; i > 0; i--) {
                    out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        (new Thread(new Server(6666, 5))).start();
    }
}
