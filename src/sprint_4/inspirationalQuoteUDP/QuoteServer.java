package sprint_4.inspirationalQuoteUDP;

import java.io.IOException;
import java.net.*;

public class QuoteServer implements Runnable {

    private InetAddress address;
    private int port;
    private final byte[] data = new byte[256];
    private byte[] message;
    int counter = 0;

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(6655)) {
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);
                    address = packet.getAddress();
                    port = packet.getPort();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
                try {
                    if (received.equalsIgnoreCase("end")) {
                        System.exit(0);
                    } else if (received.equalsIgnoreCase("quote")) {

                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        switch (counter) {
                            case 0 ->
                                    message = "“He who conquers himself is the mightiest warrior.” - Confucius".getBytes();
                            case 1 ->
                                    message = "“Opportunities don't happen, you create them.” - Chris Grosser".getBytes();
                            case 2 ->
                                    message = "“Don't let someone else's opinion of you become your reality” - Les Brown".getBytes();
                            case 3 ->
                                    message = "\"Believe you can and you're halfway there.\" - Theodore Roosevelt".getBytes();
                            case 4 ->
                                    message = "\"Weaknesses are just strengths in the wrong environment.\" - Marianne Cantwell".getBytes();
                            case 5 ->
                                    message = "\"Try to be a rainbow in someone's cloud.\" - Maya Angelou".getBytes();
                        }
                        counter++;
                        if (counter > 5) {
                            counter = 0;
                        }
                        socket.send(new DatagramPacket(message, 0, message.length, address, port));
                    }
                    else {
                        message = "write \"quote\" for a quote after 5 sec, or \"end\" to exit.".getBytes();
                        socket.send(new DatagramPacket(message, 0, message.length, address, port));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        (new Thread(new QuoteServer())).start();
    }
}
