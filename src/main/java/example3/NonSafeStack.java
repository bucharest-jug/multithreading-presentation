package example3;

import common.MStack;

public class NonSafeStack<T> implements MStack<T> {

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

    public void push(T value) {
        Node node = new Node(value, head);
        head = node;
        length += 1;
    }

    public T peek() {
        if (head != null)
            return head.value;
        else
            return null;
    }

    public T pop() {
        if (head != null) {
            T result = head.value;
            head = head.next;
            length -= 1;
            return result;
        }
        else
            return null;
    }

    public void reverse() {
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

    public int size() {
        return length;
    }
}
