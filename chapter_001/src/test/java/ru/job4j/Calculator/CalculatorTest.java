package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class calculator решение задачи части 001 урок 1.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *29.05.2017
 */
public class CalculatorTest {
	/**
	 *Тест add.
	 */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
	 *Тест subtraction.
	 */
    @Test
    public void whenSubtractionOneSubOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtraction(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }
	/**
	 *Тест Multiplication.
	 */
    @Test
    public void whenMultiplicationTwoMultTwoThenFoth() {
        Calculator calc = new Calculator();
        calc.multiplication(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
	/**
	 *Тест Division 4/2.
	 */
    @Test
    public void whenDivisionFourDivTwoThenTwo() {
        Calculator calc = new Calculator();
        calc.division(4D, 2D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
}
