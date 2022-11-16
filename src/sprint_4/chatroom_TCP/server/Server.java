package sprint_4.chatroom_TCP.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Server implements Runnable {

    private final List<ConnectionHandler> connections;
    private Properties p;

    public Server() {
        loadProperties();
        connections = new ArrayList<>();
        Thread thread = new Thread(this);
        thread.start();
    }

    private void loadProperties() {
        p = new Properties();
        try {
            p.load(new FileInputStream("res/sprint_4_TCP_chatroom/ServerProperties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (ServerSocket sSocket = new ServerSocket(Integer.parseInt(p.getProperty("serverPort", "5000")))) {
            while (true) {
                ConnectionHandler client = new ConnectionHandler(sSocket.accept());
                new Thread(client).start();
                connections.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        StringBuilder nicknameList = new StringBuilder();
        for (ConnectionHandler client : connections) {
            if (client != null) {
                nicknameList.append(client.getNickname()).append("\n");
            }
        }
        String[] messageArray = {message, nicknameList.toString()};
        for (ConnectionHandler client : connections) {
            if (client != null) {
                client.sendMessage(messageArray);
            }
        }
    }

    protected class ConnectionHandler implements Runnable {

        private final MessageHandler messageH;
        private final Socket socket;
        private String nickname;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private boolean connected = true;

        public ConnectionHandler(Socket socket) {
            this.socket = socket;
            messageH = new MessageHandler(this);
        }

        @Override
        public void run() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                System.out.println(socket.getInetAddress() + " has connected");
                while (connected) {
                    String[] message = (String[]) in.readObject();
                    messageH.handler(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(socket.getInetAddress() + " has disconnected");
            }
        }

        public void sendBroadcast(String message) {
            broadcast(message);
        }

        public void sendMessage(String[] message) {
            try {
                out.writeObject(message);
                out.flush();
                out.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        protected void disconnect() {
            connected = false;
            connections.remove(this);
            sendMessage(new String[]{"You left the chatroom"});
        }
    }
}
