package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
            private Iterator<Integer> cIterator = null;

            @Override
            public boolean hasNext() {
                if (getcIterator() == null || !getcIterator().hasNext()) {
                    if (it.hasNext()) {
                        setcIterator(it.next());
                    }
                }
                return getcIterator().hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no items");
                }
                return getcIterator().next();
            }

            public Iterator<Integer> getcIterator() {
                return cIterator;
            }

            public void setcIterator(Iterator<Integer> cIterator) {
                this.cIterator = cIterator;
            }
        };
        return integerIterator;
    }
}
