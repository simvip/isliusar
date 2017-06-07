package ru.job4j.array;

/**
 *Class Turn.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *07.06.2017
 */
public class BubbleSort {
    /**
     * back.
     *
     * @param array - digits.
     * @return array -int[].
     */
    public int[] sort(int[] array) {
        boolean doAgain = true;
        int aLenght = array.length - 1;
        int temp;
        while (doAgain) {
            doAgain = false;
            for (int i = 0; i < aLenght; i++) {
                temp = array[i];
                if (temp > array[i + 1]) {
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    doAgain = true;
                }
            }
        }
        return array;
    }
}