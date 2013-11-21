package example7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFuture<T> implements java.util.concurrent.Future<T> {
    private boolean _isDone = false;
    private T value = null;
    private Throwable error = null;

    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public boolean isCancelled() {
        return false;
    }

    public synchronized boolean isDone() {
        return _isDone;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(10, TimeUnit.DAYS);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public T get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {

        synchronized (this) {
            while (!_isDone)
                this.wait(unit.toMillis(timeout));

            if (error != null)
                if (error instanceof RuntimeException)
                    throw (RuntimeException) error;
                else
                    throw new RuntimeException(error);
            else
                return value;
        }
    }

    public synchronized void trySuccess(T value) {
        if (!_isDone) {
            this.value = value;
            this._isDone = true;
            this.notifyAll();
        }
    }

    public synchronized void tryFailure(Throwable th) {
        if (!_isDone) {
            this.error = th;
            this._isDone = true;
            this.notifyAll();
        }
    }
}
