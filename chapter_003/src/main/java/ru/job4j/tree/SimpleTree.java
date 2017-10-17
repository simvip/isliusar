package ru.job4j.tree;

/**
 * Created by Ivan Sliusar on 13.10.2017.
 * Red Line Soft corp
 * @param <E>
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent E
     * @param child  E
     * @return boolean
     */
    boolean add(E parent, E child);
}
