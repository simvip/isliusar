package ru.job4j.map;

import java.util.NoSuchElementException;

/**
 * Created by Ivan Sliusar on 10.10.2017.
 * Red Line Soft corp.
 * @param <T>
 * @param <V>
 *
 */
public class HandBook<T, V> {
    /**
     * Array of values.
     */
    private Object[] values;

    /**
     * Construct.
     */
    public HandBook() {
        this.values = new Object[100];
    }

    /**
     * Insert record.
     *
     * @param key   T
     * @param value V
     * @return boolean
     */
    public boolean insert(T key, V value) {
        if (key != null) {
            int index = getIndex(key);
            if (this.values[index] == null) {
                this.values[index] = value;
                return true;
            }
        }
        return false;
    }

    /**
     * Get index of bucket (simple version).
     *
     * @param key T
     * @return int
     */
    private int getIndex(T key) {
        int index = key.hashCode();
        do {
            index = index % values.length;
        } while (index >= values.length);
        return index;
    }

    /**
     * Get record by key.
     *
     * @param key T
     * @return V
     */
    public V get(T key) {
        int index = getIndex(key);
        if (this.values[index] != null) {
            return (V) this.values[index];
        }
        throw new NoSuchElementException();
    }

    /**
     * Delete record by key.
     *
     * @param key T
     * @return boolean
     */
    public boolean delete(T key) {
        int index = getIndex(key);
        if (this.values[index] != null) {
            this.values[index] = null;
            return true;
        }
        return false;
    }
}
