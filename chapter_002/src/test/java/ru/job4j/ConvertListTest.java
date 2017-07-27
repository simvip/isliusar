package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Admin on 27.07.2017.
 */
public class ConvertListTest {
    /**
     * Test 1. From Array to List.
     */
    @Test
    public void whenToListArrayMassiveThenListWithSameElemtnts() {
        ConvertList convertList = new ConvertList();
        int[][] ints = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        List<Integer> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 0));
        List<Integer> rezult = convertList.toList(ints);
        assertThat(expect, is(rezult));
    }

    /**
     * Test 1. From Array to List.
     */
    @Test
    public void whenToArrayList() {
        ConvertList convertList = new ConvertList();
        int[][] expect = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        List<Integer> input = new ArrayList<>();
        input.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 0));
        int[][] rezult = convertList.toArray(input, 3);
        assertThat(expect, is(rezult));
    }

}