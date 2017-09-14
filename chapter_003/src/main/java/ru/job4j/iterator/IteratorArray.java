package ru.job4j.iterator;

import java.util.Iterator;


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
    public IteratorArray() {

        this.testarray = new int[][]{
                {1, 2},
                {3, 4}
        };
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
                return !(x >= maxlength);
            }

            @Override
            public Object next() {
                Object o = testarray[x][y];
                y++;
                if (y >= maxlength) {
                    x++;
                    y = 0;
                }
                return o;
            }
        };
    }
}

