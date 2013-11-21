package example2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadingPools {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        final AtomicInteger ref = new AtomicInteger(0);

        pool.execute(new Runnable() {
            public void run() {
                String msg = String.format("Hello, World (#%d) !", ref.incrementAndGet());
                System.out.println(msg);
            }
        });

        pool.shutdown();
    }
}
