package ru.job4j.array;

import java.util.Arrays;

/**
 *Class ArrayDuplicate.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *08.06.2017
 */
public class ArrayDuplicate {
    /**
     * remove.
     *
     * @param array - String.
     * @return array -int[].
     */
    public String[] remove(String[] array) {
        int size = array.length;
        int sizeNew = 0;
        int positionDuplicate = 0;
        String tempLine = "";
        for (int i = 0; i < size; i++) {
            if (tempLine.contains(array[i])) {
                if (positionDuplicate == 0) {
                    positionDuplicate = i;
                }
            } else {
                if (positionDuplicate != 0) {
                    array[positionDuplicate] = array[i];
                    positionDuplicate = 0;
                }
                tempLine += array[i];
                sizeNew++;
            }
        }
        return Arrays.copyOf(array, sizeNew);
    }
}

