package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ivan Sliusar on 11.10.2017.
 * Red Line Soft corp.
 */
public class HandBookTest {
    /**
     * Test insert.
     */
    @Test
    public void whenInsertExistingValueThenFalse() {
        HandBook<Integer, String> handBook = new HandBook<>();
        handBook.insert(1, "Petrov");
        handBook.insert(2, "Ivanov");

        assertTrue(!handBook.insert(1, "Sidorov"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteExistingValueThenTrue() {
        HandBook<Integer, String> handBook = new HandBook<>();
        handBook.insert(300, "Petrov");
        handBook.insert(-200, "Ivanov");

        boolean result = handBook.delete(300);
        assertTrue(result);
    }

    /**
     * Test get.
     */
    @Test
    public void whenGetbyKeyExistingValueThenReturnValue() {
        HandBook<Integer, String> handBook = new HandBook<>();
        handBook.insert(1, "Petrov");
        handBook.insert(2, "Ivanov");

        String result = handBook.get(1);
        assertTrue("Petrov".equals(result));
    }

}