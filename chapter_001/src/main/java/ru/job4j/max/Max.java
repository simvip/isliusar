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
}