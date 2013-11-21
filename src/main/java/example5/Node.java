package example5;

public class Node<T> {
    public final T value;
    public final Node<T> next;
    public final boolean isNil;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
        this.isNil = false;
    }

    private Node() {
        this.value = null;
        this.next = this;
        this.isNil = true;
    }

    private static Node<Object> nil = new Node<Object>();

    @SuppressWarnings("unchecked")
    public static <T> Node<T> Nil() {
        return (Node<T>) nil;
    }
}
