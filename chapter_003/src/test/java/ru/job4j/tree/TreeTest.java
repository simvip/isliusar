package ru.job4j.tree;

import org.junit.Test;

/**
 * Created by Ivan Sliusar on 17.10.2017.
 * Red Line Soft corp.
 */
public class TreeTest {
    /**
     * Test elementary tree.
     */
    @Test
    public void testTree() {
        Tree<String> tree = new Tree();
        tree.add(null, "Root");
        tree.add("Root", "Test1");
        tree.add("Root", "Test2");
        tree.add("Root", "Test3");

        tree.add("Test1", "Test01");
        tree.add("Test1", "Test02");
        tree.add("Test1", "Test03");

        for (String value : tree) {
            System.out.println(value);
        }

    }
}
