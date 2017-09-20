package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static ru.job4j.list.SimplLinkedList.Rout.BACK;
import static ru.job4j.list.SimplLinkedList.Rout.FORWARD;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public class SimplLinkedList<T> implements SimpleContainer {
    /**
     * Head of list.
     */
    private Node head;
    /**
     * Tail of list.
     */
    private Node tail;
    /**
     * Size list.
     */
    private int size;

    /**
     * Head of list.
     */
    private Node currentIterNod;

    /**
     * Construct.
     */
    public SimplLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    /**
     * Enum Rout of step.
     */
    enum Rout {
        /**
         * FORWARD.
         */
        FORWARD,
        /**
         * Back.
         */
        BACK
    }

    /**
     * Override Iterator.
     *
     * @return Iterator Iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                if (currentIterNod == null) currentIterNod = SimplLinkedList.this.head;
                return (SimplLinkedList.this.currentIterNod != null) && (SimplLinkedList.this.currentIterNod.next != null);
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("ups");
                SimplLinkedList.this.currentIterNod = SimplLinkedList.this.currentIterNod.next;
                return (T) SimplLinkedList.this.currentIterNod.value;
            }
        };
    }

    /**
     * Add to list.
     *
     * @param o Object
     */
    @Override
    public void add(Object o) {
        Node node = new Node(o);
        if (this.tail != null) {
            this.tail.next = node;
            node.previous = this.tail;
        } else {
            this.head.next = node;
            node.previous = this.head;
        }
        this.tail = node;
        this.size++;
    }

    /**
     * Get from list by index.
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (this.size < index) throw new NoSuchElementException("ups");

        Node currentNod;
        Rout step;
        if (size / 2 >= index) {
            currentNod = this.head.next;
            step = FORWARD;
        } else {
            currentNod = this.tail;
            step = BACK;
            index = index - (size / 2 + (size % 2) > 0 ? 1 : 0);
        }

        while (index != 0) {
            currentNod = step == FORWARD ? currentNod.next : currentNod.previous;
            index--;
        }
        return (T) currentNod.value;
    }

    /**
     * Class NODe for implementation of linked list.
     */
    private class Node {
        /**
         * Previous Node
         */
        Node previous;
        /**
         * Next Node.
         */
        Node next;
        /**
         * Value.
         */
        Object value;

        /**
         * Construct.
         * @param value Object.
         */
        Node(Object value) {
            this.value = value;
        }
    }
}
