package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class max digits.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *02.06.2017
 */
public class MaxTest {
	/**
	 *Test max.
	 */
	@Test
	public void whenMaxOneMaximumZeroThenOne() {
		Max max = new Max();
		int result = max.max(1, 0);
		int expected = 1;
		assertThat(result, is(expected));
	}
	/**
	 *Test max.
	 */
	@Test
	public void whenMaxThreeMaximumPhfiveMaximumEigthThenEigth() {
		Max max = new Max();
		int result = max.max(3, 5, 8);
		int expected = 8;
		assertThat(result, is(expected));
	}
}