package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Sliusar on 30.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public class SetByLinkedList<T> implements Iterable<T> {

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
     * Construct.
     */
    public SetByLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    /**
     * Override Iterator.
     *
     * @return Iterator Iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            private Node currentIterNod;

            @Override
            public boolean hasNext() {
                if (this.currentIterNod == null) {
                    this.currentIterNod = SetByLinkedList.this.head;
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

    public void add(T o) {
        boolean needAdd = true;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == o) {
                needAdd = false;
                break;
            }
        }

        if (needAdd) {
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
