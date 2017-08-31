package ru.job4j;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (int[] i : array) {
            for (int j : i) {
                rezult.add(j);
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
        int lines = 0;
        int row = 0;
        for (int value : list) {
            array[lines][row] = value;
            row++;
            if (row > rows - 1) {
                lines++;
                row = 0;
            }
        }
        return array;
    }

    /**
     * Convert merger all list in one.
     *
     * @param list List
     * @return List
     */
    public List<Integer> convert(List<int[]> list) {
        List rezult = new ArrayList<Integer>();
        for (int[] mass : list) {
            rezult.addAll(Arrays.asList(mass));
        }
        return rezult;
    }

}
