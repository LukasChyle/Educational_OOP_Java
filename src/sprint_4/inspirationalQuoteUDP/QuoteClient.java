package sprint_4.inspirationalQuoteUDP;

import java.io.*;
import java.net.*;

public class QuoteClient implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private final byte[] data = new byte[256];

    @Override
    public void run() {
        int outPort = 6664;
        int inPort = 6665;

        try {
            InetAddress address = InetAddress.getLocalHost();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            socket = new DatagramSocket(inPort);
            String input;

            while (true) {
                try {
                    input = in.readLine();
                    byte[] message = input.getBytes();
                    socket.send(new DatagramPacket(message,0, message.length, address, outPort));
                    if (input.equalsIgnoreCase("end")) {
                        System.exit(0);
                    }
                    packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(packet.getData(), 0, packet.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert socket != null;
            socket.close();
        }
    }

    public static void main(String[] args) {
        (new Thread(new QuoteClient())).start();
    }
}
