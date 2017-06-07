package ru.job4j.array;

/**
 *Class Rotate.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *07.06.2017
 */
public class RotateArray {
    /**
     * back.
     *
     * @param array - digits.
     * @return array -int[].
     */
    public int[][] rotate(int[][] array) {
        int size = array.length;
        int newColm = size - 1;
        int[][] newArray = new int[size][size];
        for (int line = 0; line < size; line++) {
            for (int colm = 0; colm < size; colm++) {
                newArray[colm][newColm] = array[line][colm];
            }
            newColm--;
        }
          return newArray;
    }
}