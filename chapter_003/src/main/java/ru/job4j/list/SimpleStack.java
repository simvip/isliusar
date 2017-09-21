package ru.job4j.list;

/**
 * Created by Ivan Sliusar on 20.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public class SimpleStack<T> {
    /**
     * SimplLinkedList Objects.
     */
    private SimplLinkedList<T> objects;

    /**
     * Construct.
     */
    public SimpleStack() {
        this.objects = new SimplLinkedList<T>();
    }

    /**
     * Pop object.
     * @return element T
     */
    public T pop() {
        T element = objects.get(0);
        objects.delete(0);
        return element;
    }

    /**
     * Add object.
     *
     * @param value T
     */
    public void push(T value) {
        this.objects.add(value);
    }
}
