package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Created by Ivan Sliusar on 12.09.2017.
 * Red Line Soft corp.
 */
public class IteratorArray implements Iterable {
    /**
     * Array value.
     */
    private int[][] testarray;
    /**
     * current index (coordinate x).
     */
    private int x = 0;
    /**
     * current index (coordinate y).
     */
    private int y = 0;
    /**
     * max index.
     */
    private int maxlength;

    /**
     * Construct.
     */
    public IteratorArray(int[][] testarray) {
        this.testarray = testarray;
        this.maxlength = this.testarray.length;
    }

    /**
     * Iterator.
     *
     * @return Iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return x < testarray.length && !(x > testarray[x].length);
            }

            @Override
            public Object next() {
                if (!hasNext()) throw new NoSuchElementException("no items");

                Object o = testarray[x][y];
                y++;
                if (y >= testarray[x].length) {
                    x++;
                    y = 0;
                }

                return o;
            }
        };
    }
}

