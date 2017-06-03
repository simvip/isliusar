package ru.job4j.condition;

/**
 *Class condition.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *03.06.2017
 */
public class Point {
	/**
	 *Координата х.
	 */
	private int x;
	/**
	 *Координата у.
	 */
	private int y;
	/**
	 *point.
	 *@param x - Первый аргумент.
	 *@param y - Второй аргумент.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 *getter x.
	 *@return int x.
	 */
	public int getX() {
		return this.x;
	}
	/**
	 *getter y.
	 *@return int y.
	 */
	public int getY() {
		return this.y;
	}
	/**
	 *is.
	 *@param a - Первый аргумент.
	 *@param b - Второй аргумент.
	 *@return boolean rezult.
	 */
	public boolean is(int a, int b) {
		int y = getY();
		int x = getX();
		return y == a * x + b;
	}
}