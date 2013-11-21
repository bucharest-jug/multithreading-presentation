package common;

public interface BetterMStack<T> {
    void push(T value);
    Optional<T> peek();
    Optional<T> pop();
    int size();
    void reverse();
}
