package example5;

import common.IStack;
import common.Optional;
import common.Pair;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class ImmutableStack<T> implements IStack<T>, Iterable<T> {
    private final Node<T> root;
    private final int length;

    private ImmutableStack(Node<T> root, int length) {
        this.length = length;
        this.root = root;
    }

    private ImmutableStack() {
        this.root = Node.Nil();
        this.length = 0;
    }

    public IStack<T> push(T elem) {
        Node<T> newRoot = new Node<>(elem, root);
        return new ImmutableStack<>(newRoot, length + 1);
    }

    @Override
    public Pair<Optional<T>, IStack<T>> pop() {
        if (root.isNil)
            return Pair.of(Optional.<T>absent(), (IStack<T>) this);
        else {
            T value = root.value;

            return Pair.of(
                Optional.of(value),
                (IStack<T>) new ImmutableStack<>(root.next, length - 1)
            );
        }
    }

    public Optional<T> peek() {
        if (root.isNil)
            return Optional.absent();
        else
            return Optional.of(root.value);
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return root.isNil;
    }

    public IStack<T> reversed() {
        IStack<T> reversed = new ImmutableStack<>();
        for (Node<T> cursor = root; !cursor.isNil; cursor = cursor.next)
            reversed = reversed.push(cursor.value);
        return reversed;
    }

    @SuppressWarnings("unchecked")
    public static <T> IStack<T> empty() {
        return (IStack<T>) emptyStack;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cursor = root;

            public boolean hasNext() {
                return !cursor.isNil;
            }

            @Override
            public T next() {
                T value = cursor.value;
                cursor = cursor.next;
                return value;
            }

            @Override
            public void remove() {
                throw new IllegalAccessError();
            }
        };
    }

    public int hashCode() {
        int emptyHash = empty().hashCode();
        if (isEmpty())
            return emptyHash;
        else {
            int chash = this.hash.get();
            if (chash != emptyHash)
                return chash;
            else {
                int nhash = emptyHash;

                for (T value : this)
                    nhash = nhash ^ value.hashCode();
                this.hash.compareAndSet(chash, nhash);
                return nhash;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IStack<?>))
            return false;

        Node<T> cursor1 = root;
        Node<?> cursor2 = ((ImmutableStack<?>) obj).root;

        while (cursor1 != Node.Nil() && cursor2 != Node.Nil()) {
            if (cursor1.value == null || cursor2.value == null) {
                if (cursor1.value != cursor2.value)
                    return false;
            }
            else if (!cursor1.value.equals(cursor2.value))
                return false;

            cursor1 = cursor1.next;
            cursor2 = cursor2.next;
        }

        return cursor1 == Node.Nil() && cursor2 == Node.Nil();
    }

    private final AtomicInteger hash = new AtomicInteger(-984719305);
    private static final IStack<?> emptyStack = new ImmutableStack<>();
}
