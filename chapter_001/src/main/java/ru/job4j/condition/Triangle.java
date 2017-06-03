package ru.job4j.condition;

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
	 *Координата a.
	 */
	private Point a;
	/**
	 *Координата b.
	 */
	private Point b;
	/**
	 *Координата c.
	 */
	private Point c;
	/**
	 *point.
	 *@param a - Первый аргумент.
	 *@param b - Второй аргумент.
	 *@param c - Третий аргумент.
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
		return  (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2;
	}
}