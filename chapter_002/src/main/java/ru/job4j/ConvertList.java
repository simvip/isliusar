package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация двумерного массива в ArrayList и наоборот.
 */
public class ConvertList {
    /**
     * From Array to List.
     *
     * @param array int[][]
     * @return list<Integer>
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> rezult = new ArrayList<>();
        int size = array.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rezult.add(array[i][j]);
            }
        }
        return rezult;
    }

    /**
     * From list to array.
     *
     * @param list List<Integer> input list
     * @param rows int rows
     * @return int[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];
        int indexList = 0;
        int maxIndex = list.size() - 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                int value = 0;
                if (indexList < maxIndex) {
                    value = list.get(indexList);
                    indexList++;
                }
                array[i][j] = value;
            }
        }
        return array;
    }
}
