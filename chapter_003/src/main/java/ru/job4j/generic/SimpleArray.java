package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 * @param <T> This is the type parameter
  */
public class SimpleArray<T> {
    /**
     * Array objects.
     */
    private Object[] objects;
    /**
     * Current index.
     */
    private int index;

    /**
     * Construct.
     *
     * @param size int
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
        this.index = 0;
    }

    /**
     * Add to collection.
     * @param value T
     */
    public void add(T value) {
        objects[index++] = value;
    }

    /**
     * Get value from collection by index.
     * @param position int
     * @return T
     */
    public T get(int position) {
        return (T) this.objects[position];
    }

    /**
     * Update from collection.
     * @param position int
     * @param value T
     */

    public void update(int position, T value) {
        objects[position] =  value;
    }

    /**
     * Delete from collection.
     * @param value T
     */

    public void delete(T value) {
        for (int i = 0; i <= index; i++) {
            if (objects[i] == value) {
                objects[i] = null;
            }
        }
    }



}
