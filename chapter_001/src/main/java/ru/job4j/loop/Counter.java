package ru.job4j.loop;

/**
 *Class Counter.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *03.06.2017
 */
 public class Counter {
	/**
	 *Add.
	 *
	 *@param start - Начало.
	 *@param finish - Конец.
	 *@return rezult int.
	 */
	 public int add(int start, int finish) {
		 int rezult = 0;
		 for (int i = start; i <= finish; i++) {
			 if (i % 2 == 0) {
				 rezult += i;
			 }
		 }
		 return rezult;
	 }
 }