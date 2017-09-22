package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ivan Sliusar on 22.09.2017.
 * Red Line Soft corp.
 */
public class CycleDetectionTest {
    /**
     * Test.
     */
    @Test
    public void hasCycle() {

        CycleDetection detection = new CycleDetection();
        Node<Integer> first = new Node(1);
        Node<Integer> two = new Node(2);
        Node<Integer> third = new Node(3);
        Node<Integer> four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        assertTrue(detection.hasCycle(first));
    }
}