package ru.job4j.list;

/**
 * Created by Ivan Sliusar on 19.09.2017.
 * Red Line Soft corp.
 * @param <E>
 */
interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Add.
     * @param e E
     */
    void add(E e);

    /**
     * Get.
     * @param index int
     * @return E
     */
    E get(int index);
}
