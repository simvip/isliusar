package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class Factorial.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *04.06.2017
 */
public class FactorialTest {
	/**
	 *Test 1.
	 */
	@Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
    }
	/**
	 *Test 1.
	 */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
    }
}