package ru.job4j.loop;

/**
 *Class Board.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *04.06.2017
 */
public class Paint {
	/**
	 *paint.
	 *
	 *@param hight - digits.
	 *@return result String.
	 */
	public String piramid(int hight) {
		String separator = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		int width = (2 * hight) - 1;
		for (int y = hight; y > 0; y--) {
			for (int x = 1; x <= width; x++) {
				if (belongsOrNot(hight, width, x, y)) {
					sb.append("^");
				} else {
                    sb.append(" ");
				}
            }
			if (y != 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	/**
	 *belongsOrNot.
	 *
	 *@param hight - digits.
	 *@param width - digits.
	 *@param pointX - digits.
	 *@param pointY - digits.
	 *@return result - boolean.
	 */
	private boolean belongsOrNot(int hight, int width, int pointX, int pointY) {
		int p1X = hight; int p1Y = hight;
		int p2X = width; int p2Y = 1;
		int p3X = 1; int p3Y = 1;
		int a = (p1X - pointX) * (p2Y - p1Y) - (p2X - p1X) * (p1Y - pointY);
		int b = (p2X - pointX) * (p3Y - p2Y) - (p3X - p2X) * (p2Y - pointY);
		int c = (p3X - pointX) * (p1Y - p3Y) - (p1X - p3X) * (p3Y - pointY);

		return ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0));
	}
}