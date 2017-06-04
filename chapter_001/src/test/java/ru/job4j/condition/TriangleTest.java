package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 *Class triangle.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *03.06.2017
 */
public class TriangleTest {
	/**
	 *Test max.
	 */
    @Test
    public void whenIsFistArgTwoSecondArgTwoThenThrue() {
		Point a = new Point(2, -3);
		Point b = new Point(1, 1);
		Point c = new Point(-6, 5);
		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.area();
		double expected = 12;
        assertThat(result, closeTo(expected, 0.01));
    }
}