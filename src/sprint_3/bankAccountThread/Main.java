package sprint_3.bankAccountThread;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount("4562-456917", 1000);

        Thread thread1 = new Thread(() -> {

            account.deposit("thread 1", 300);

            account.withdraw("thread 1", 50);

        });

        Thread thread2 = new Thread(() -> {

            account.deposit("thread 2", 203.75);

            account.withdraw("thread 2", 100);

        });

        Thread thread3 = new Thread(() -> {

            account.deposit("thread 3", 400);

            account.withdraw("thread 3", 350);

        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
