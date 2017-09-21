package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 20.09.2017.
 * Red Line Soft corp.
 */
public class SimpleQueueTest {

    /**
     * Test.
     */
    @Test
    public void simpleQueueTest() {
        SimpleQueue queue = new SimpleQueue();
        queue.push("Кот Васька");
        queue.push("Кошка Муся");
        queue.push("Кот Рыжик");
        queue.push("Кошка Гаечка");

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        assertThat(queue.pop(), is("Кот Васька"));
    }

}