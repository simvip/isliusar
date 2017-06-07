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
public class TurnTest {
    /**
     *Test 1.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        int[] testMass = {2, 6, 1, 4};
        Turn turn = new Turn();
        int[] resultArray = turn.back(testMass);
        int[] expectArray = {4, 1, 6, 2};
        assertThat(resultArray, is(expectArray));
    }
    /**
     *Test 2.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        int[] testMass = {1, 2, 3, 4, 5};
        Turn turn = new Turn();
        int[] resultArray = turn.back(testMass);
        int[] expectArray = {5, 4, 3, 2, 1};
        assertThat(resultArray, is(expectArray));
    }
}
