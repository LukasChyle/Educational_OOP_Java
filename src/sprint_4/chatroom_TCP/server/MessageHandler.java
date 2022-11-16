package sprint_4.chatroom_TCP.server;

public class MessageHandler {

    private final Server.ConnectionHandler connection;

    protected MessageHandler(Server.ConnectionHandler connection) {
        this.connection = connection;
    }

    public void handler(String[] in) {
        if (in[0] != null) {
            connection.disconnect();
            connection.sendBroadcast(connection.getNickname() + " has left the chatroom");
        }

        if (in.length >= 2 && in[1] != null && !in[1].isBlank()) {
            connection.sendBroadcast(connection.getNickname() + ": " + in[1]);
        }

        if (in.length >= 3 && in[2] != null && !in[2].isBlank()) {
            String tempOld = connection.getNickname();
            connection.setNickname(in[2]);
            if (tempOld != null) {
                connection.sendBroadcast(tempOld + " has changed nickname to " + in[2]);
            } else {
                connection.sendBroadcast(in[2] + " has joined the chatroom");
            }
        }
    }
}
