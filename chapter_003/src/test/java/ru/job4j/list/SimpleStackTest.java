package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 20.09.2017.
 * Red Line Soft corp.
 */
public class SimpleStackTest {
    /**
     * Test.
     */
    @Test
    public void simpleStackTest() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("Кот Васька");
        stack.push("Кошка Муся");
        stack.push("Кот Рыжик");
        stack.push("Кошка Гаечка");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        assertThat(stack.pop(), is("Кошка Гаечка"));
    }
}