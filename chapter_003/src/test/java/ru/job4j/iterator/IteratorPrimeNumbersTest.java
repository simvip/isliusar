package ru.job4j.iterator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Ivan Sliusar on 14.09.2017.
 * Red Line Soft corp.
 */
public class IteratorPrimeNumbersTest {
    /**
     * Test.
     */
    @Test
    public void whenInputArrayThenReturnArrayWithEventNumber() {
        IteratorPrimeNumbers iterator = new IteratorPrimeNumbers(new int[]{3, 4, 5, 6, 7});
        int[] rezult = new int[3];
        int index = 0;
        while (iterator.hasNext()) {
            rezult[index++] = (int) iterator.next();
        }
        assertArrayEquals(new int[]{3, 5, 7}, rezult);
    }
}