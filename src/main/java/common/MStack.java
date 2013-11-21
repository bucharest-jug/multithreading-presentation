package common;

public interface MStack<T> {
    void push(T value);
    T peek();
    T pop();
    int size();
    void reverse();
}
