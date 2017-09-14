package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Sliusar on 14.09.2017.
 * Red Line Soft corp.
 */
public class IteratorPrimeNumbers implements Iterator {
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
     * @param numbers int[]
     */
    public IteratorPrimeNumbers(final int[] numbers) {
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
            if (isPrime(numbers[i])) {
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
            if (isPrime(numbers[i])) {
                this.index = i + 1;
                return numbers[i];
            }
        }

        throw new NoSuchElementException();
    }

    /**
     * Является ли число простым?
     * @return boolean
     * @param n int
     */

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
