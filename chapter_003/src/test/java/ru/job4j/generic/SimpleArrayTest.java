package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
public class SimpleArrayTest {
    /**
     * Test add.
     */
    @Test
    public void add() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test");
        assertThat(simpleArray.get(0), is("Test"));
    }

    /**
     * Test get.
     */
    @Test
    public void get() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test");
        assertThat(simpleArray.get(0), is("Test"));
    }

    /**
     * Test update.
     */
    @Test
    public void update() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test");
        simpleArray.update(0, "Test2");
        assertThat(simpleArray.get(0), is("Test2"));
    }

    /**
     * Test delete.
     */
    @Test
    public void delete() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test");
        simpleArray.add("Test1");
        simpleArray.delete("Test");
        assertNull(simpleArray.get(0));
    }
}