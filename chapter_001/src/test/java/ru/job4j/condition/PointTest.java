package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class condition.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *03.06.2017
 */
public class PointTest {
	/**
	 *Тест add.
	 */
    @Test
    public void whenIsFistArgTwoSecondArgTwoThenThrue() {
		Point point = new Point(2, 6);
		boolean result = point.is(2, 2);
		boolean expected = true;
        assertThat(result, is(expected));
    }
}
