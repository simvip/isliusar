package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class TestTask.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *13.06.2017
 */
public class TestTaskTest {
    /**
     *Test 1.
     */
    @Test
    public void whenOriginAndSubStingEqualThenTurnedTrue() {
        TestTask testTask = new TestTask();
        String origin = "Какой чудесный день для программирования";
        String sub = "день";
        boolean result = testTask.contains(origin, sub);
        assertThat(true, is(result));
    }
    /**
     *Test 2.
     */
    @Test
    public void whenOriginAndSubStingNotEqualThenTurnedFalce() {
        TestTask testTask = new TestTask();
        String origin = "Какой чудесный день для программирования";
        String sub = "не так ли";
        boolean result = testTask.contains(origin, sub);
        assertThat(false, is(result));
    }
}
