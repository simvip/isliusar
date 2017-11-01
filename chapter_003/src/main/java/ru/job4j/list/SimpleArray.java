package ru.job4j.list;

import java.util.Iterator;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 * @param <T>
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

            @Override
            public boolean hasNext() {
                return (SimpleArray.this.curentIndex + 1) < SimpleArray.this.values.length;
            }

            @Override
            public T next() {
                return (T) SimpleArray.this.values[SimpleArray.this.curentIndex++];
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
    public synchronized T get(int index) {
        return (T) this.values[index];
    }

    @Override
    public synchronized void add(Object o) {
        if ((this.curentIndex + 1) == this.values.length) {
            growArray();
        }
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
//class SimpleArrayIterator<T> implements Iterator<T>{
//    @Override
//    public boolean hasNext() {
//        return false;
//    }
//
//    @Override
//    public T next() {
//        return null;
//    }
//}
//class ArrayIterrator implements Iterator<T>
