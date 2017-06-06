package ru.job4j.loop;

/**
 *Class Board.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *04.06.2017
 */
public class Board {
	/**
	 *paint.
	 *
	 *@param width - digits.
	 *@param height - digits.
	 *@return result String.
	 */
	public String paint(int width, int height) {
		String curentChar = "x";
		String separator = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sb.append(curentChar);
				curentChar = "x".equals(curentChar) ? " " : "x";
			}
			if (i != height) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
}