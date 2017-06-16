package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class TestTask.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *16.06.2017
 */
public class ConcatenateTest {
    /**
     *Test 1.
     */
    @Test
    public void whenFistTestFistArrayPastLastArrayThenResultSortConcatArray() {
        int[] fistArray = {2, 5, 8, 9};
        int[] lastArray = {10, 11, 30, 57, 62};
        Concatenate concatenate = new Concatenate();
        int[] resultArray = concatenate.concatenateArray(fistArray, lastArray);
        int[] expectArray = {2, 5, 8, 9, 10, 11, 30, 57, 62};
        assertThat(resultArray, is(expectArray));
    }
    /**
     *Test 2.
     */
    @Test
    public void whenSecondTestFistArrayPastLastArrayThenResultSortConcatArray() {
        int[] fistArray = {2, 3, 15, 17};
        int[] lastArray = {1, 6, 16, 18, 62};
        Concatenate concatenate = new Concatenate();
        int[] resultArray = concatenate.concatenateArray(fistArray, lastArray);
        int[] expectArray = {1, 2, 3, 6, 15, 16, 17, 18, 62};
        assertThat(resultArray, is(expectArray));
    }
    /**
     *Test 3.
     */
    @Test
    public void whenThridTestFistArrayPastLastArrayThenResultSortConcatArray() {
        int[] fistArray = {2, 6, 15, 17};
        int[] lastArray = {1, 6, 16, 18, 62};
        Concatenate concatenate = new Concatenate();
        int[] resultArray = concatenate.concatenateArray(fistArray, lastArray);
        int[] expectArray = {1, 2, 6, 6, 15, 16, 17, 18, 62};
        assertThat(resultArray, is(expectArray));
    }
}
