package ru.job4j.calculator;

/**
 *Class calculator.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *29.05.2017
 */
public class Calculator {
	/**
	 *Локальная переменная для хранения результата.
	 */
    private double result;
    /**
	 *add производит сложение двух чисел.
	 *@param first - Первый аргумент.
	 *@param second - Второй аргумент.
	 */
    public void add(double first, double second) {
        this.result = first + second;
    }
	/**
	 *Subtraction производит вычитание двух чисел.
	 *@param first - Первый аргумент.
	 *@param second - Второй аргумент.
	 */
    public void subtraction(double first, double second) {
        this.result = first - second;
    }
	/**
	 *Subtraction производит вычитание двух чисел.
	 *@param first - Первый аргумент.
	 *@param second - Второй аргумент.
	 */
    public void multiplication(double first, double second) {
        this.result = first * second;
    }
	/**
	 *Subtraction производит вычитание двух чисел.
	 *@param first - Первый аргумент.
	 *@param second - Второй аргумент.
	 */
    public void division(double first, double second) {
		this.result = first / second;
	}
	/**
	 *Возвращает результата.
	 *@return возвращает тип double.
	 */
    public double getResult() {
        return this.result;
    }
}