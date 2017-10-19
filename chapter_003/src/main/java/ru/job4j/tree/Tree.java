package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ivan Sliusar on 13.10.2017.
 * Red Line Soft corp.
 *
 * @param <E>
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Root of tree.
     */
    private Node root;

    /**
     * Utility class Node.
     */
    private class Node implements Comparable<E> {
        /**
         * Left node.
         */
        private Node left = null;

        /**
         * Right node.
         */
        private Node right = null;

        /**
         * List of children.
         */
        private List<Node> children;

        /**
         * Value.
         */
        private E value;

        /**
         * Construct.
         *
         * @param value E
         */
        private Node(E value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        /**
         * Construct.
         * @param left Node
         * @param right Node
         * @param value E
         */
        private Node(Node left, Node right, E value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        /**
         * Override compare.
         *
         * @param o Object
         * @return int
         */
        @Override
        public int compareTo(E o) {
            return this.value.compareTo(o);
        }
    }

    private int maxheight(Node node, int max){
        if (node.children!=null){
            max = Math.max(max,node.children.size());
            for (Node current :
                    node.children) {
                max = maxheight(current,max);
            }
        }
        return max;
    }

    /**
     * This tree binary?.
     * @return boolean
     */
    public boolean isBinary() {
        return (maxheight(root, 0)<= 2);
    }

    /**
     * Add.
     */
    public boolean add(E value) {
        if (root==null) {
            root = new Node(value);
            return true;
        }

        return findParentByValue(new Node(value), root);
    }

    private boolean findParentByValue(Node newNode, Node currentNode){

        if (newNode.value.compareTo(currentNode.value) == 1){
            if (currentNode.right == null)
                currentNode.right = newNode;
            else
                findParentByValue(newNode, currentNode.right);
        } else {
            if (currentNode.left == null)
                currentNode.left = newNode;
            else
                findParentByValue(newNode, currentNode.right);
        }
        return true;
    }

    /**
     * Add value in tree.
     *
     * @param parent parent
     * @param child  child
     * @return boolean
     */
    @Override
    public boolean add(E parent, E child) {
        if (parent==null && child == null) return false;

        for(E value: this){
            if (value.compareTo(child)==0){
                return false;
            }
        }
        if (root ==null && parent == null) {
            root = new Node(child);
            return true;
        }
        Node parentRoot = giveNodeByValue(root, parent);
        if (parentRoot != null) {
            parentRoot.children.add(new Node(child));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Iterator.
     *
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator() {
        List<E> nods = new ArrayList<>();
        if (root == null) {
            return nods.iterator();
        }
        return returnValueByTree(root, nods).iterator();
    }

    /**
     * Return all value by tree.
     *
     * @param node     Node
     * @param allValue List
     * @return list
     */
    private List<E> returnValueByTree(Node node, List<E> allValue) {
        allValue.add(node.value);
        if (!(node.children == null)) {
            for (Node cNode : node.children) {
                returnValueByTree(cNode, allValue);
            }
        }
        return allValue;
    }

    /**
     * Give node by Value.
     *
     * @param node      Node
     * @param findValue E
     * @return Node
     */
    private Node giveNodeByValue(Node node, E findValue) {
        Node result = null;
        if (node.value.compareTo(findValue) == 0) {
            return node;
        }
        for (Node item : node.children) {
            result = giveNodeByValue(item, findValue);
            if (result != null) {
                return result;
            }
        }
        return result;
    }
}
