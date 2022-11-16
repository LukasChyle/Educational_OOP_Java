package sprint_4.chatroom_TCP.client;

public enum ConnectStatus { DISCONNECTED("Disconnected"), CONNECTED("Connected");

    private final String status;

    ConnectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
