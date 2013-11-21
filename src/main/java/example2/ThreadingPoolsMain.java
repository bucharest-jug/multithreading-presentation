package example2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadingPoolsMain {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        final AtomicInteger ref = new AtomicInteger(0);

        Runnable r = new Runnable() {
            public void run() {
                String msg = String.format("Hello, World (#%d) !", ref.incrementAndGet());
                System.out.println(msg);
            }
        };

        pool.execute(r);
        pool.execute(r);
        pool.execute(r);
        pool.execute(r);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        pool.shutdown();
    }
}
