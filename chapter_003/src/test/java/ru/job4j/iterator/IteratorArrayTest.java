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
        IteratorArray array = new IteratorArray();
        for (Object current : array) {
            System.out.println(current);
        }
    }

}