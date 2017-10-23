package ru.job4j.tree;

import org.junit.Test;

/**
 * /**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class BSTTest {

    /**
     * Test 1.
     */
    @Test
    public void testBST() {
        BST<Integer> bst = new BST<>();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);

        for (Integer value :
                bst) {
            System.out.println(value);
        }
    }
}
