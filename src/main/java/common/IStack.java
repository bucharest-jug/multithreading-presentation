package common;

public interface IStack<T> {
    IStack<T> push(T elem);

    Pair<Optional<T>, IStack<T>> pop();

    Optional<T> peek();

    int size();

    IStack<T> reversed();

    boolean isEmpty();
}
