package sprint_3.messagesThread;

class Message {
    private String message;
    private boolean empty = true;

    public synchronized void write(String message) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException, class: Message (write)");
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }

    public synchronized String read() {
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException, class: Message (read)");
            }
        }
        empty = true;
        notifyAll();
        return message;
    }
}