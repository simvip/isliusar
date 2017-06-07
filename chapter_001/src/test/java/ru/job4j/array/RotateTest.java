package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class RotateTest.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *07.06.2017
 */
public class RotateTest {
    /**
     *Test 1.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        int[][] testMass = {
                {1, 2},
                {3, 4}
        };
        RotateArray rotate = new RotateArray();
        int[][] resultArray = rotate.rotate(testMass);
        int[][] expectArray = {
                {3, 1},
                {4, 2}
        };
        assertThat(resultArray, is(expectArray));
    }
    /**
     *Test 1.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        int[][] testMass = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RotateArray rotate = new RotateArray();
        int[][] resultArray = rotate.rotate(testMass);
        int[][] expectArray = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        assertThat(resultArray, is(expectArray));
    }
}
