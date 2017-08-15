package ru.job4j.recursion;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Admin on 15.08.2017.
 */
public class ExchangeTest {
    /**
     * Test 1.
     */
    @Test
    public void whenInputHandredThenHave292() {
        int rezult = Exchange.getCountOfWays(100, 4);
        assertThat(292, is(rezult));
    }
}