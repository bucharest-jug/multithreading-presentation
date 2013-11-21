package example1;

import java.math.BigDecimal;

public class Main {
    public static Thread createActor(final BankAccount account, final BigDecimal withdrawAmount) {
        Thread th = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (!account.withdraw(withdrawAmount))
                        break;

                    incrementWithdrawn(withdrawAmount);
                }
            }
        });

        th.start();
        return th;
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(new BigDecimal(400000));

        Thread thread1 = createActor(account, new BigDecimal(4));
        Thread thread2 = createActor(account, new BigDecimal(10));

        thread1.join();
        thread2.join();

        System.out.println("New amount: " + account.getAmount().toString());
        System.out.println("Total withdrawn: " + getWithdrawn().toString());
    }

    private static final Object lock = new Object();
    private static BigDecimal totalWithDrawn = BigDecimal.ZERO;

    static void incrementWithdrawn(BigDecimal amount) {
        synchronized (lock) {
            totalWithDrawn = totalWithDrawn.add(amount);
        }
    }

    static BigDecimal getWithdrawn() {
        synchronized (lock) {
            return totalWithDrawn;
        }
    }
}
