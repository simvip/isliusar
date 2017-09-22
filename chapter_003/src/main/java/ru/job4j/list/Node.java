package ru.job4j.list;

/**
 * Created by Ivan Sliusar on 22.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public class Node<T> {
    /**
     * Construct.
     * @param value T
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Value.
     */
    private T value;

    /**
     * link by next Node.
     */
    private Node<T> next;

    /**
     * Getter.
     * @return T
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter.
     * @param value T
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Getter.
     * @return T
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setter.
     * @param next T
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
