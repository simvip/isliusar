package ru.job4j.max;

/**
 *Class max digits.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *02.06.2017
 */
public class Max {
	/**
	 *Возвращает максимальное число из двух.
	 *@param first args.
	 *@param second args.
	 *@return rezult int.
	 */
	public int max(int first, int second) {
		return first > second ? first : second;
	}
	/**
	 *Возвращает максимальное число из трех.
	 *@param first args.
	 *@param second args.
	 *@param third args.
	 *@return rezult int.
	 */
	public int max(int first, int second, int third) {
		return max(max(first, second), max(second, third));
	}
}