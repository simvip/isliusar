package ru.job4j.tree;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public interface Visitor<E> {
    void visit(E item);
}
