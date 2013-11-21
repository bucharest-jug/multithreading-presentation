package example6;

import common.IStack;
import common.Optional;
import common.Pair;
import example5.ImmutableStack;

public final class ImmutableQueue<T> {
    private final IStack<T> enqueueStack;
    private final IStack<T> dequeueStack;
    private final int length;

    private ImmutableQueue(IStack<T> enqueueStack, IStack<T> dequeueStack, int length) {
        this.dequeueStack = dequeueStack;
        this.enqueueStack = enqueueStack;
        this.length = length;
    }

    public ImmutableQueue() {
        dequeueStack = ImmutableStack.empty();
        enqueueStack = ImmutableStack.empty();
        length = 0;
    }

    public int size() {
        return length;
    }

    public ImmutableQueue<T> enqueue(T value) {
        return new ImmutableQueue<>(
            enqueueStack.push(value),
                dequeueStack,
            length + 1
        );
    }

    public Pair<Optional<T>, ImmutableQueue<T>> dequeue() {
        if (!dequeueStack.isEmpty()) {
            Pair<Optional<T>, IStack<T>> pair = dequeueStack.pop();

            ImmutableQueue<T> newQueue = new ImmutableQueue<>(
                    enqueueStack,
                    pair.collection(),
                    length - 1
            );

            return Pair.of(pair.value(), newQueue);
        }
        else if (!enqueueStack.isEmpty()) {
            IStack<T> newDequeueStack = enqueueStack.reversed();
            ImmutableQueue<T> newQueue = new ImmutableQueue<>(
                ImmutableStack.<T>empty(),
                newDequeueStack,
                length
            );

            return newQueue.dequeue();
        }
        else
            return Pair.of(Optional.<T>absent(), this);
    }

    public Optional<T> peek() {
        return dequeue().value();
    }

    public boolean isEmpty() {
        return length == 0;
    }
}
