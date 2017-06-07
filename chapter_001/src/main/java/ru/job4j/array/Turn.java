package ru.job4j.array;

/**
 *Class Turn.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *07.06.2017
 */
public class Turn {
    /**
     * back.
     *
     * @param array - digits.
     * @return array -int[].
     */
    public int[] back(int[] array) {
        int maxIter = (int) array.length / 2;
        int tempInt;
        int maxIndex = array.length - 1;
        for (int i = 0; i < maxIter; i++) {
            tempInt = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = tempInt;
        }
        return array;
    }
}