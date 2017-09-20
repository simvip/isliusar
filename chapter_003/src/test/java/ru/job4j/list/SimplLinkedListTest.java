package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 20.09.2017.
 * Red Line Soft corp.
 */
public class SimplLinkedListTest {

    /**
     * Test.
     */
    @Test
    public void simplLinkedListTest() {
        SimplLinkedList<String> linkedList = new SimplLinkedList();
        linkedList.add("Apple");
        linkedList.add("Garlic");
        linkedList.add("Pear");
        linkedList.add("Watermelon");
        linkedList.add("Plum");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        for (Object value : linkedList) {
            System.out.println(value);
        }
        assertThat(linkedList.get(4), is("Watermelon"));
    }
}