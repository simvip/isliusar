package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class EngineerTest .
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *21.06.2017
 */
public class EngineerTest {
    /**
     * doSomething.
     */
    @Test
    public void whenDoSomethingForYangThenGet100() {
        Person children = new Person("Саня", "Горшков", 4, 100, 2);
        Person tolik = new Person("Анатолий", "Очумелый", 20, 100, 99);
        Engineer engineer = new Engineer(100, tolik);

        engineer.doSomething(children);
        int result = engineer.getOrder();
        int expected = 100;
        assertThat(result, is(expected));
    }

    /**
     * getOrder.
     */
    @Test
    public void whenGetOrderForOldHumanThenGetZero() {
        Person oldFarter = new Person("Вениамин", "Пирожков", 101, 1, 2);
        Person tolik = new Person("Анатолий", "Очумелый", 20, 100, 99);
        Engineer engineer = new Engineer(100, tolik);

        engineer.doSomething(oldFarter);
        int result = engineer.getOrder();
        int expected = 0;
        assertThat(result, is(expected));
    }

}