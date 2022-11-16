package sprint_4.chatroom_TCP.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

public class Client extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JScrollPane chatWindowScroll, clientsWindowScroll, messageWindowScroll;
    private JTextArea chatWindow, clientsWindow, messageWindow;
    private JButton connectB, sendB;
    private JTextField nicknameField;
    private Properties p;
    private boolean connected;
    private ObjectOutputStream out;
    private String nickname;

    public Client() {
        setVisible(true);
        setContentPane(mainPanel);
        setTitle("TCP Chatroom");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        loadProperties();

        connectB.setText(ConnectStatus.DISCONNECTED.getStatus());
        connectB.setBackground(Color.red);
        connectB.addActionListener(this);
        sendB.addActionListener(this);
        nicknameField.addActionListener(this);
        nicknameField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                setNickname();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (connectB.getText().equals(ConnectStatus.CONNECTED.getStatus())) {
                    connected = false;
                    sendMessage(new String[]{"exit"});
                }
                dispose();
            }
        });
    }

    private void setNickname() {
        if (!nicknameField.getText().isBlank() && !nicknameField.getText().trim().equals(nickname)) {
            nickname = nicknameField.getText().trim();
            if (connectB.getText().equals(ConnectStatus.CONNECTED.getStatus())) {
                sendMessage(new String[]{null, null, nickname});
            }
        } else if (nickname != null) {
            nicknameField.setText(nickname);
        }
    }

    private void loadProperties() {
        p = new Properties();
        try {
            p.load(new FileInputStream("res/sprint_4_TCP_chatroom/ClientProperties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setConnection() {
        if (nickname == null) {
            chatWindow.append("Can't connect without a nickname\n");
            return;
        }
        if (connectB.getText().equals(ConnectStatus.DISCONNECTED.getStatus())) {
            connected = true;
            new Thread(task).start();
            connectB.setText(ConnectStatus.CONNECTED.getStatus());
            connectB.setBackground(Color.green);
            return;
        }
        connected = false;
        sendMessage(new String[]{"exit"});
        connectB.setText(ConnectStatus.DISCONNECTED.getStatus());
        connectB.setBackground(Color.red);
        clientsWindow.setText("");
    }

    private void sendMessage(String[] message) {
        if (connectB.getText().equals(ConnectStatus.CONNECTED.getStatus())) {
            try {
                out.writeObject(message);
                out.flush();
                out.reset();
                messageWindow.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chatWindow.append("Can't sen message when your offline\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectB) {
            setConnection();

        } else if (e.getSource() == sendB) {
            sendMessage(new String[]{null, messageWindow.getText()});

        } else if (e.getSource() == nicknameField) {
            setNickname();
        }
    }

    Runnable task = () -> {
        try (Socket socket = new Socket(InetAddress.getByName(p.getProperty("serverHost", "5000")),
                Integer.parseInt(p.getProperty("serverPort", "5000")));
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            out = new ObjectOutputStream(socket.getOutputStream());
            sendMessage(new String[]{null, null, nickname});
            while (connected) {
                String[] message = (String[]) in.readObject();

                if (message[0] != null) {
                    chatWindow.append(message[0] + "\n");
                }
                if (message.length >= 2 && message[1] != null) {
                    clientsWindow.setText(message[1]);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
        Client client = new Client();
    }
}
