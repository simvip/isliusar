package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class ArrayDuplicateTest.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *08.06.2017
 */
public class ArrayDuplicateTest {
    /**
    *Test 1.
    */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] testMass = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = arrayDuplicate.remove(testMass);
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(result, is(expected));
    }
}