package ru.job4j.condition;

import static java.lang.Math.abs;
/**
 *Class triangle.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *03.06.2017
 */
public class Triangle {
	/**
	 *Вершина a.
	 */
	private Point a;
	/**
	 *Вершина b.
	 */
	private Point b;
	/**
	 *Вершина c.
	 */
	private Point c;
	/**
	 *Треугольник.
	 *@param a - Вершина.
	 *@param b - Вершина.
	 *@param c - Вершина.
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 *area.
	 *@return double rezult.
	 */
	public double area() {
		int ax = this.a.getX();
		int ay = this.a.getY();
		int bx = this.b.getX();
		int by = this.b.getY();
		int cx = this.c.getX();
		int cy = this.c.getY();
		int rezult = abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)));
		return   rezult / 2.0;
	}
}