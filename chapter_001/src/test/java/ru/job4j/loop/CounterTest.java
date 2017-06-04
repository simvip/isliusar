package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class Counter.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *04.06.2017
 */
public class CounterTest {
	/**
	 *Test.
	 */
	@Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter couter = new Counter();
		int result = couter.add(1, 10);
		int expected = 30;
		assertThat(result, is(expected));
    }
}