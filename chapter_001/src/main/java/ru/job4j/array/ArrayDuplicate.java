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
        String tempLine = "";
        for (int i = 0; i < size; i++) {
            if (!tempLine.contains(array[i])) {
                if (sizeNew != i) {
                    array[sizeNew] = array[i];
                }
                sizeNew++;
                tempLine += array[i];
            }
        }
        return Arrays.copyOf(array, sizeNew);
    }
}

