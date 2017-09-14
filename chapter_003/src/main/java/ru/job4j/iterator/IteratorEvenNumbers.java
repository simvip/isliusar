package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Sliusar on 14.09.2017.
 * Red Line Soft corp.
 */
public class IteratorEvenNumbers implements Iterator {
    /**
     * test array.
     */
    private int[] numbers;
    /**
     * current index.
     */
    private int index = 0;

    /**
     * Construct.
     *
     * @param numbers
     */
    public IteratorEvenNumbers(final int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * hasNext override.
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        boolean haveEven = false;

        for (int i = this.index; i < numbers.length; i++) {
           if (numbers[i] % 2 == 0) {
                haveEven = true;
                break;
            }
        }
        return haveEven;
    }

    /**
     * next() override.
     *
     * @return Object
     * @throws NoSuchElementException
     */
    @Override
    public Object next() throws NoSuchElementException {

        for (int i = this.index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                this.index = i+1;
                return numbers[i];
            }
        }

        throw new NoSuchElementException();
    }
}
