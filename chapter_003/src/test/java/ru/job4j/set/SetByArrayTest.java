package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 30.09.2017.
 * Red Line Soft corp.
 */
public class SetByArrayTest {
    /**
     * Test add to Set.
     */
    @Test
    public void whenAddToSetValueWithContainedInSetAddDoNothing() {
        SetByArray<String> setByArray = new SetByArray<>(10);

        setByArray.add("fist");
        setByArray.add("second");
        setByArray.add("third");

        setByArray.add("third");
        int maxValueInSet = 0;
        for (Object value : setByArray) {
            System.out.println((String) value);
            maxValueInSet++;
        }

        assertThat(maxValueInSet, is(3));
    }

}