package sprint_4.contactList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {

    enum clientStatus {
        CONNECTED("Connected"), DISCONNECTED("Disconnected");
        final String s;

        clientStatus(String s) {
            this.s = s;
        }
    }

    private JPanel mainPanel, topPanel, addPanel;
    private JTextField searchField, name, phone, mail;
    private JLabel headLabel, searchLabel, nameLabel, phoneLabel, mailLabel;
    private JButton connectionButton, searchButton, addButton, deleteButton;
    private JTextArea textArea;
    ObjectOutputStream outStream;
    Thread thread;

    public Client() {
        setVisible(true);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        connectionButton.setBackground(Color.red);

        connectionButton.addActionListener(this);
        searchButton.addActionListener(this);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchField.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (connectionButton.getText().equals(clientStatus.CONNECTED.s)) {
                    sendData(new String[]{"close"});
                }
                dispose();
            }
        });
    }

    private void add() {
        if (connectionButton.getText().equals(clientStatus.CONNECTED.s)) {
            if (!name.getText().isBlank() && !phone.getText().isBlank() && !mail.getText().isBlank()) {
                sendData(new String[]{"add", name.getText().trim(), phone.getText().trim(), mail.getText().trim()});
                name.setText("");
                phone.setText("");
                mail.setText("");
            } else {
                textArea.setText("Fields for\nname, phone number and e-mail\nhave to be filled to add a contact");
            }
        } else {
            textArea.setText("Can't add contact when disconnected");
        }
    }

    private void search() {
        if (connectionButton.getText().equals(clientStatus.CONNECTED.s)) {
            if (!searchField.getText().isBlank()) {
                sendData(new String[]{"search", searchField.getText().trim()});
                searchField.setText("");
            }
        } else {
            textArea.setText("Can't search contact when disconnected");
        }
    }

    private void delete() {
        if (connectionButton.getText().equals(clientStatus.CONNECTED.s)) {
            if (!searchField.getText().isBlank()) {
                sendData(new String[]{"delete", searchField.getText().trim()});
                searchField.setText("");
            }
        } else {
            textArea.setText("Can't delete contact when disconnected");
        }
    }

    private void sendData(String[] message) {
        try {
            outStream.writeObject(message);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connection() {
        if (connectionButton.getText().equals(clientStatus.DISCONNECTED.s)) {
            new Thread(task).start();
            connectionButton.setBackground(Color.green);
            connectionButton.setText(clientStatus.CONNECTED.s);
            return;
        }
        sendData(new String[]{"close"});
        connectionButton.setBackground(Color.red);
        connectionButton.setText(String.valueOf(clientStatus.DISCONNECTED.s));
    }

    Runnable task = () -> {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
             ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())) {
            outStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Person person = (Person) inStream.readObject();

                if (person == null) {
                    textArea.setText("Contact was not found");
                } else if (person.getMessage().equals("close")) {
                    break;
                } else if (person.getMessage().equals("exists")) {
                    textArea.setText("Contact with that name, phone number or e-mail already exists");
                } else if (person.getMessage().equals("found")) {
                    textArea.setText(person.getName() + "\n" + person.getPhone() + "\n" + person.getMail());
                } else if (person.getMessage().equals("added")) {
                    textArea.setText("Contact was added:\n" + person.getName() +
                            "\n" + person.getPhone() + "\n" + person.getMail());
                } else if (person.getMessage().equals("deleted")) {
                    textArea.setText("Contact was deleted:\n" + person.getName() +
                            "\n" + person.getPhone() + "\n" + person.getMail());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchField || e.getSource() == searchButton) {
            search();
        } else if (e.getSource() == addButton) {
            add();
        } else if (e.getSource() == deleteButton) {
            delete();
        } else if (e.getSource() == connectionButton) {
            connection();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}