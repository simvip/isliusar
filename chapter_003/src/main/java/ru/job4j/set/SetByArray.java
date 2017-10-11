package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Sliusar on 30.09.2017.
 * Red Line Soft corp.
 *
 * @param <T>
 */
public class SetByArray<T> implements Iterable<T> {
    /**
     * Array values.
     */
    private Object[] values;
    /**
     * max values in array.
     */
    private int countValue;

    /**
     * Construct.
     *
     * @param size int
     */
    public SetByArray(int size) {
        this.values = new Object[size];
        this.countValue = 0;
    }

    /**
     * Override Iterator.
     *
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return this.currentIndex + 1 <= countValue;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("The end of the Set");
                }
                return (T) SetByArray.this.values[this.currentIndex++];
            }
        };
    }

    /**
     * Add value to Set.
     *
     * @param value T
     */
    public void add(T value) {
        boolean needAdd = true;
        for (int i = 0; i < this.countValue; i++) {
            if (value != null && value.equals(this.values[i])) {
                needAdd = false;
                break;
            }
        }
        if (needAdd) {
            this.values[countValue++] = value;
        }
    }

    /**
     * Add value to Set.
     *
     * @param value T
     */
    public void addheshCode(T value) {
        int index = indexFor(value.hashCode(), this.values.length - 1);

        this.values[index] = value;

    }

    /**
     * Index For.
     * @param hash int
     * @param length int
     * @return int
     */
    private int indexFor(int hash, int length) {
        int cHash = Math.max(hash, hash * -1);
        while (cHash > length) {
            cHash = cHash / length;
        }
        return cHash;
    }
}
