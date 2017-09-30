package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 30.09.2017.
 * Red Line Soft corp.
 */
public class SetByLinkedListTest {
    /**
     * Test.
     */
    @Test
    public void whenAddToSetValueWithContainedInSetAddDoNothing() {
        SetByLinkedList<String> setByLinkedList = new SetByLinkedList<>();
        setByLinkedList.add("first");
        setByLinkedList.add("second");
        setByLinkedList.add("third");

        setByLinkedList.add("third");

        int maxValueInSet = 0;
        for (Object value : setByLinkedList) {
            System.out.println((String) value);
            maxValueInSet++;
        }

        assertThat(maxValueInSet, is(3));
    }

}