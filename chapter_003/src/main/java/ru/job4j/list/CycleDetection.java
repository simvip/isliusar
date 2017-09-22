package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 22.09.2017.
 * Red Line Soft corp.
 */
public class CycleDetection {
    /**
     * Test linked list fore looping.
     *
     * @param first Node.
     * @return boolean
     */
    public boolean hasCycle(Node first) {

        List<Node> uniqueValue = new ArrayList<>();

        while (first.getNext() != null) {
            if (uniqueValue.contains(first.getNext())) {
                return true;
            } else {
                uniqueValue.add(first.getNext());
            }
            first = first.getNext();
        }
        return false;
    }
}

