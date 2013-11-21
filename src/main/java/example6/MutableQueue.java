package example6;

import common.Optional;
import common.Pair;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class MutableQueue<T> {
    private final AtomicReference<ImmutableQueue<T>> ref;

    public MutableQueue() {
        ref = new AtomicReference<>(new ImmutableQueue<T>());
    }

    public void enqueue(T value) {
        boolean isSuccess;
        do {
            ImmutableQueue<T> current = ref.get();
            ImmutableQueue<T> update = current.enqueue(value);
            isSuccess = ref.compareAndSet(current, update);
        } while(!isSuccess);

        synchronized (ref) {
            ref.notify();
        }
    }

    public Optional<T> dequeue() {
        while (true) {
            ImmutableQueue<T> current = ref.get();
            if (current.isEmpty())
                return Optional.absent();

            Pair<Optional<T>, ImmutableQueue<T>> result = current.dequeue();
            if (ref.compareAndSet(current, result.collection()))
                return result.value();
        }
    }

    public T dequeueAwait() throws TimeoutException {
        return awaitDequeue(TimeUnit.SECONDS, 10);
    }

    public T awaitDequeue(TimeUnit unit, int value) throws TimeoutException {
        long timeout = System.currentTimeMillis() + unit.toMillis(value);
        Optional<T> result = Optional.absent();

        while (result.isAbsent()) {
            if (timeout <= System.currentTimeMillis())
                throw new TimeoutException("dequeueAwait");

            result = dequeue();

            if (result.isAbsent())
                try {
                    synchronized (ref) {
                        if (ref.get().isEmpty())
                            ref.wait(300);
                    }
                }
                catch (InterruptedException ignored) {}
        }

        return result.get();
    }

    public void awaitEmpty(TimeUnit unit, int value)
            throws InterruptedException, TimeoutException {

        long timeout = System.currentTimeMillis() + unit.toMillis(value);
        while (!ref.get().isEmpty()) {
            if (timeout <= System.currentTimeMillis())
                throw new TimeoutException("dequeueAwait");

            synchronized (ref) {
                if (!ref.get().isEmpty())
                    ref.wait(300);
            }
        }
    }
}
