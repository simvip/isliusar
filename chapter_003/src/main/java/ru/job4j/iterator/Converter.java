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
            Iterator<Integer> cIterator = null;

            @Override
            public boolean hasNext() {
                if (cIterator == null || !cIterator.hasNext()) {
                    if (it.hasNext()) cIterator = it.next();
                }
                return cIterator.hasNext();
            }

            @Override
            public Integer next() {
                return cIterator.next();
            }
        };
        return integerIterator;
    }
}
