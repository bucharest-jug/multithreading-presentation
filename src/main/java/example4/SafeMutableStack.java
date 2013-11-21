package example4;

import common.BetterMStack;
import common.Optional;

public class SafeMutableStack<T> implements BetterMStack<T> {
    protected class Node {
        public T value;
        public Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    protected Node head = null;
    protected int length = 0;

    public synchronized void push(T value) {
        head = new Node(value, head);
        length += 1;
    }

    public synchronized Optional<T> peek() {
        if (head != null)
            return Optional.of(head.value);
        else
            return Optional.absent();
    }

    public synchronized Optional<T> pop() {
        if (head != null) {
            T result = head.value;
            head = head.next;
            length -= 1;
            return Optional.of(result);
        }
        else
            return Optional.absent();
    }

    public synchronized void reverse() {
        if (head == null) return;
        Node prev = null;
        Node cursor = head;

        while (cursor != null) {
            Node next = cursor.next;
            cursor.next = prev;
            prev = cursor;
            cursor = next;
        }

        head = prev;
    }

    public synchronized int size() {
        return length;
    }
}
