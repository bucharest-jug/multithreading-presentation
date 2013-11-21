package example7;

import example6.MutableQueue;
import java.util.concurrent.*;

public abstract class Actor {
    abstract protected Object receive(Object message);

    public Future<Object> send(Object message) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        MessageAndFuture pair = new MessageAndFuture(message, future);
        messages.enqueue(pair);
        return future;
    }

    private final Thread thread = createThread();
    private volatile boolean isRunning = true;
    private final MutableQueue<MessageAndFuture> messages =
        new MutableQueue<>();

    private void loop() {
        while (isRunning) {
            MessageAndFuture pair = null;
            boolean gotMessage;

            try {
                pair = messages.awaitDequeue(TimeUnit.SECONDS, 1);
                gotMessage = true;
            } catch (TimeoutException e) {
                gotMessage = false;
            }

            if (gotMessage)
                try {
                    Object result = receive(pair.message);
                    pair.future.trySuccess(result);
                }
                catch (Throwable th) {
                    pair.future.tryFailure(th);
                }
        }
    }

    private Thread createThread() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                loop();
            }
        });

        th.start();
        return th;
    }

    public void join(TimeUnit unit, int timeout)
            throws TimeoutException, InterruptedException {
        messages.awaitEmpty(unit, timeout);
    }

    public void shutdown() throws InterruptedException {
        isRunning = false;
        thread.join(10000);
    }
}
