package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
public class Converter {

    /**
     * Convert.
     *
     * @param it Iterator
     * @return Iterator
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        Iterator<Integer> integerIterator = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Integer next() {
                return it.next().next();
            }
        };
        return integerIterator;
    }
}
