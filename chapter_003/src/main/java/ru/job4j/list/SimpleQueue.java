package ru.job4j.list;

/**
 * Created by Ivan Sliusar on 20.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public class SimpleQueue<T> {
    /**
     * SimplLinkedList Objects.
     */
    private SimplLinkedList<T> objects;

    /**
     * Construct.
     */
    public SimpleQueue() {
        this.objects = new SimplLinkedList<T>();
    }

    /**
     * Pop object.
     * @return element T
     */
    public T pop() {
        int maxSize = objects.getSize();
        T element = objects.get(maxSize);
        objects.delete(maxSize);
        return element;
    }

    /**
     * Add object.
     *
     * @param value t
     */
    public void push(T value) {
        this.objects.add(value);
    }
}
