package sprint_4.contactList;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private final Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())) {
            System.out.println("Socket: " + socket.getPort() + " connected");
            while (true) {
                String[] message = (String[]) inStream.readObject();
                outStream.reset();
                outStream.writeObject(Databas.objectHandler(message));
                outStream.flush();
                if (message[0].equals("close")) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Socket: " + socket.getPort() + " closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
