package ru.job4j.loop;

/**
 *Class Factorial.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *04.06.2017
 */
public class Factorial {
	/**
	 *Add.
	 *
	 *@param n - digits.
	 *@return rezult int.
	 */
	public int calc(int n) {
		 int rezult = 1;
		 for (int i = 1; i <= n; i++) {
			 rezult *= i;
		 }
		 return rezult;
	}
}