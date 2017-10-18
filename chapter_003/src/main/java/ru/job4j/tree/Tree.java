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
