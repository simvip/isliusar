package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 */
public class SimpleArrayTest {

    /**
     * Test.
     */
    @Test
    public void testSimpleArrayInteger() {
        SimpleArray<Integer> array = new SimpleArray<>();
        for (int i = 0; i < 20; i++) {
            array.add(i);
        }
        assertThat(array.get(1), is(1));
    }

    /**
     * Test.
     */
    @Test
    public void testSimpleArrayString() {
        SimpleArray<Integer> array = new SimpleArray<>();
        for (int i = 0; i < 20; i++) {
            array.add("Test" + i);
        }
        assertThat(array.get(1), is("Test1"));
    }

}