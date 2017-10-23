package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class BST<E extends Comparable<E>> implements Iterable<E> {

    /**
     * Add value in tree.
     *
     * @param value E
     * @return boolean
     */
    public boolean add(E value) {
        if (value == null) return false;
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }

        return findAndSetParentNode(root, newNode);
    }


    /**
     * Utility method witch find parent by new node.
     *
     * @param parentNode Node
     * @param newNode    Node
     * @return
     */
    private boolean findAndSetParentNode(Node parentNode, Node newNode) {
        if (newNode.value.compareTo(parentNode.value) == -1) {
            if (parentNode.leftNode == null)
                parentNode.leftNode = newNode;
            else
                findAndSetParentNode(parentNode.leftNode, newNode);
        } else {
            if (parentNode.rightNode == null)
                parentNode.rightNode = newNode;
            else
                findAndSetParentNode(parentNode.rightNode, newNode);
        }
        return true;
    }

    /**
     * Root of tree.
     */
    Node root;

    /**
     * Utility class NODe
     */
    private class Node {
        /**
         * Left NODe.
         */
        public Node leftNode;
        /**
         * Right NODe.
         */
        public Node rightNode;
        E value;

        /**
         * Construct with additional parameters.
         *
         * @param leftNode  Node
         * @param rightNode Node
         * @param value     E
         */
        public Node(Node leftNode, Node rightNode, E value) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.value = value;
        }

        /**
         * Construct.
         *
         * @param value E
         */
        public Node(E value) {
            this.value = value;
        }
    }

    /**
     * Override iterator.
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return IteratorTree();
    }

    /**
     * Iterrator tree.
     *
     * @return Iterator
     */
    private Iterator<E> IteratorTree() {

        List<E> list = new ArrayList<E>();

        left2right(root, new Visitor<E>() {
            @Override
            public void visit(E item) {
                list.add(item);
            }
        });
        return list.iterator();
    }

    /**
     * Bypassing tree
     */
    private void left2right(Node n, Visitor visitor) {
        if (n != null) {
            left2right(n.leftNode, visitor);
            visitor.visit(n.value);
            left2right(n.rightNode, visitor);
        }
    }


}
