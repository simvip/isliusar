package ru.job4j.list;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 */
interface SimpleContainer<E> extends Iterable<E>{
    void add(E e);
    E get(int index);
}
