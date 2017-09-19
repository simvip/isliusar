package ru.job4j.list;

import java.util.Iterator;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 */
public class SimpleArray<T> implements SimpleContainer {
    /**
     * array of values.
     */
    private Object[] values;

    /**
     * Current max index of array.
     */
    private int curentIndex;

    /**
     * Construct.
     *
     * @param size int
     */
    public SimpleArray(int size) {
        this.values = new Object[size];
        this.curentIndex = 0;
    }

    /**
     * Overload construct.
     */
    public SimpleArray() {
        this.values = new Object[10];
        this.curentIndex = 0;
    }

    /**
     * Iterator of array.
     *
     * @return Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            SimpleArray<T> tSimpleArray = SimpleArray.this;

            @Override
            public boolean hasNext() {
                return (tSimpleArray.curentIndex + 1) < tSimpleArray.values.length;
            }

            @Override
            public T next() {
                return (T) tSimpleArray.values[tSimpleArray.curentIndex++];
            }
        };
    }


    /**
     * Get values from array.
     *
     * @param index int index in array.
     * @return
     */
    @Override
    public T get(int index) {
        return (T) this.values[index];
    }

    @Override
    public void add(Object o) {
        if ((this.curentIndex + 1) == this.values.length) growArray();
        this.values[curentIndex++] = o;
    }

    /**
     * Grow array.
     */
    private void growArray() {
        int newSize = this.values.length * 2;
        Object[] newArray = new Object[newSize];
        for (int i = 0; i < this.values.length; i++) {
            newArray[i] = this.values[i];
        }
        this.values = newArray;
    }
}
//private class SimpleArrayIterator implements Iterator<T>
//class ArrayIterrator implements Iterator<T>
