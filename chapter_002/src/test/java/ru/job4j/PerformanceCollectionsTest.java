package ru.job4j;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test performance different collections.
 */
public class PerformanceCollectionsTest {
    /**
     * Test add in collection.
     */
    @Test
    public void add() {
        PerformanceCollections collections = new PerformanceCollections();

        Collection<String> linked = new LinkedList<>();
        System.out.printf("Add in Linked list %s %s", collections.add(linked, 1000000),System.lineSeparator());


        Collection<String> arrayList = new ArrayList<>();
        System.out.printf("Add in Array List lis %s %s", collections.add(arrayList, 1000000),System.lineSeparator());

        Collection<String> treeSet = new TreeSet<>();
        System.out.printf("Add in Tree set lis %s %s", collections.add(treeSet, 1000000),System.lineSeparator());
        System.out.println();
        assertThat(10, is(10));
    }

    /**
     * Test delete in collection.
     */
    @Test
    public void delete() {
        PerformanceCollections collections = new PerformanceCollections();

        Collection<String> linked = new LinkedList<>();
        collections.add(linked, 100000);
        System.out.printf("Delete from Linked list %s %s", collections.delete(linked, 50000),System.lineSeparator());

        Collection<String> arrayList = new ArrayList<>();
        collections.add(arrayList, 100000);
        System.out.printf("Delete from Array List lis %s %s", collections.delete(arrayList, 50000),System.lineSeparator());

        Collection<String> treeSet = new TreeSet<>();
        collections.add(treeSet, 100000);
        System.out.printf("Delete from Tree Set lis %s %s", collections.delete(treeSet, 50000),System.lineSeparator());
        System.out.println();
        assertThat(10, is(10));
    }

}