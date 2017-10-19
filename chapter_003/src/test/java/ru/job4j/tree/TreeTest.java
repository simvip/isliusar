package ru.job4j.tree;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Ivan Sliusar on 17.10.2017.
 * Red Line Soft corp.
 */
public class TreeTest {
    /**
     * Test elementary tree.
     */
    @Test
    public void whenTreeisBinaryThenTrue() {
        Tree<String> tree = new Tree();
        tree.add(null, "Root");
        tree.add("Root", "Test1");
        tree.add("Root", "Test2");


        tree.add("Test1", "Test01");
        tree.add("Test1", "Test02");


        tree.add("Test2", "Test03");
        assertTrue(tree.isBinary());
    }

    /**
     * Test elementary tree.
     */
    @Test
    public void whenTreeisNotBinaryThenFalse() {
        Tree<String> tree = new Tree();
        tree.add(null, "Root");
        tree.add("Root", "Test1");
        tree.add("Root", "Test2");
        tree.add("Root", "Test3");


        tree.add("Test1", "Test01");
        tree.add("Test1", "Test02");


        tree.add("Test2", "Test03");
        assertFalse(tree.isBinary());
    }

    /**
     * Test search binary tree.
     */
    @Test
    public void TestBinaryTree() {
        Tree<Integer> tree = new Tree();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);

    }
}
