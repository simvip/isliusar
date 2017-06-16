package ru.job4j.array;

/**
 *Class Rotate.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *16.06.2017
 */
public class Concatenate {
    /**
     * back.
     *
     * @param fist - fist array.
     * @param last - last array.
     * @return  int[].
     */
    public int[] concatenateArray(int[] fist, int[] last) {

        int[] result = new int[fist.length + last.length];
        int indexFist = 0; int indexLast = 0;

        for (int i = 0; i < result.length; i++) {

            if (indexFist < fist.length & indexLast < last.length) {
                if (fist[indexFist] > last[indexLast]) {
                    result[i] = last[indexLast];
                    indexLast++;
                } else {
                    result[i] = fist[indexFist];
                    indexFist++;
                }
            } else if (indexFist < fist.length) {
                result[i] = fist[indexFist];
                indexFist++;
            } else if (indexLast < last.length) {
                result[i] = last[indexLast];
                indexLast++;
            }

        }
        return result;
    }
}
