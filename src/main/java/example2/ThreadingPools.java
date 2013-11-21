package example2;

import java.util.concurrent.ForkJoinPool;

public class ThreadingPools {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(new Runnable() {
            public void run() {
                System.out.println("Hello, World!");
            }
        });

        pool.shutdown();
    }
}
