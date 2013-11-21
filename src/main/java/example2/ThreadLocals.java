package example2;

import java.util.Random;

public class ThreadLocals {

    static Random rnd = new Random();
    static synchronized int generateRandom() {
        return rnd.nextInt();
    }

    static final ThreadLocal<Integer> someInt = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return generateRandom();
        }
    };

    static Thread createConsumer(final String name) {
        return new Thread(new Runnable() {
            public void run() {
                int initialValue = someInt.get();

                int test = someInt.get();
                assert(test == initialValue);

                System.out.println(
                    String.format("Value returned by %s: %d", name, initialValue)
                );
            }
        });
    }


    public static void main(String[] args) throws InterruptedException {
        Thread th1 = createConsumer("Thread 1");
        Thread th2 = createConsumer("Thread 2");

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(
            "Value read by main: " + someInt.get());
    }
}
