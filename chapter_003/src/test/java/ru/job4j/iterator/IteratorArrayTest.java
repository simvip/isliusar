package ru.job4j.iterator;

import org.junit.Test;


/**
 * Created by Ivan Sliusar on 13.09.2017.
 * Red Line Soft corp.
 */
public class IteratorArrayTest {

    /**
     * Test foreach.
     */
    @Test
    public void foreachTest() {
        int[][] value = {
                {1, 2},
                {3, 4}
        };
        IteratorArray array = new IteratorArray(value);

        for (Object current : array) {
            System.out.println(current);
        }

        int[][] value2 = {{1, 2}, {3}};
        array = new IteratorArray(value2);

        for (Object current : array) {
            System.out.println(current);
        }
    }

}