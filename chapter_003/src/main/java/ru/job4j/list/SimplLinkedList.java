package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static ru.job4j.list.SimplLinkedList.Rout.BACK;
import static ru.job4j.list.SimplLinkedList.Rout.FORWARD;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 *
 * @param <T>
 */
@ThreadSafe
public class SimplLinkedList<T> implements SimpleContainer {
    /**
     * Head of list.
     */
    @GuardedBy("this")
    private Node head;
    /**
     * Tail of list.
     */
    @GuardedBy("this")
    private Node tail;
    /**
     * Size list.
     */
    @GuardedBy("this")
    private int size;


    /**
     * Construct.
     */
    public SimplLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    /**
     * Getter size.
     *
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * Delete element.
     *
     * @param index int
     */
    public synchronized void delete(int index) {
        Node node = getNode(index);

        Node pNode = node.previous;
        Node nNode = node.next;

        pNode.next = nNode;

        if (nNode != null) {
            nNode.previous = pNode;
        }
        if (this.tail == node) {
            this.tail = nNode != null ? nNode : pNode;
        }

        node.previous = null;
        node.next = null;
        this.size--;
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
    public synchronized Iterator iterator() {
        return new Iterator() {
            /**
             * Head of list.
             */
            private Node currentIterNod;
            @Override
            public boolean hasNext() {
                if (currentIterNod == null) {
                    currentIterNod = SimplLinkedList.this.head;
                }
                return (currentIterNod != null) && (currentIterNod.next != null);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("ups");
                }
                currentIterNod = currentIterNod.next;
                return (T) currentIterNod.value;
            }
        };
    }

    /**
     * Add to list.
     *
     * @param o Object
     */
    @Override
    public synchronized void add(Object o) {
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
    public synchronized T get(int index) {
        return (T) getNode(index).value;
    }

    /**
     * Get nod by index.
     *
     * @param index int
     * @return node Node
     */
    private Node getNode(int index) {
        if (this.size < index) {
            throw new NoSuchElementException("ups");
        }

        Node currentNod;
        Rout step;
        if (size / 2 >= index) {
            currentNod = this.head.next;
            step = FORWARD;
        } else {
            currentNod = this.tail;
            step = BACK;
            index = this.size - index;
        }

        while (index != 0) {
            currentNod = step == FORWARD ? currentNod.next : currentNod.previous;
            index--;
        }
        return currentNod;
    }

    /**
     * Class NODe for implementation of linked list.
     */
    private class Node {
        /**
         * Previous Node.
         */
        private Node previous;
        /**
         * Next Node.
         */
        private Node next;
        /**
         * Value.
         */
        private Object value;

        /**
         * Construct.
         *
         * @param value Object.
         */
        Node(Object value) {
            this.value = value;
        }

        /**
         * Getter previous.
         *
         * @return Node
         */
        public Node getPrevious() {
            return previous;
        }

        /**
         * Setter previous.
         *
         * @param previous Node
         */
        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        /**
         * Getter next.
         *
         * @return Node.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Setter next.
         *
         * @param next Node.
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Getter value.
         *
         * @return value Object
         */
        public Object getValue() {
            return value;
        }


    }
}
