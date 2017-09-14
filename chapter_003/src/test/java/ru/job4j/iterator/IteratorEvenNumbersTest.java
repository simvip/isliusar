package ru.job4j.iterator;

import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Ivan Sliusar on 14.09.2017.
 * Red Line Soft corp.
 */
public class IteratorEvenNumbersTest {

    /**
     * Test.
     */
    @Test
    public void whenInputArrayThenReturnArrayWithEventNumber() {
        IteratorEvenNumbers iteratorEvenNumbers = new IteratorEvenNumbers(new int[]{4, 2, 1, 1});
        int[] rezult = new int[2];
        int index = 0;
        while (iteratorEvenNumbers.hasNext()) {
            rezult[index++] = (int) iteratorEvenNumbers.next();
        }
        assertArrayEquals(new int[]{4, 2}, rezult);
    }
}