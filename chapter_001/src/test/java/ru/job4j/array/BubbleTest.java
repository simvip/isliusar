package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class PaintTest.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *07.06.2017
 */
public class BubbleTest {
    /**
     *Test 1.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] testMass = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        BubbleSort bubbleSort = new BubbleSort();
        int[] resultArray = bubbleSort.sort(testMass);
        int[] expectArray = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(resultArray, is(expectArray));
    }
}
