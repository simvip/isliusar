package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 * @param <T>
 */
public interface Store<T extends Base> {
    /**
     * Add.
     *
     * @param value T
     */
    void add(T value);

    /**
     * Delete.
     *
     * @param value T
     */
    void delete(T value);

    /**
     * Update.
     *
     * @param value T
     */
    void update(T value);

    /**
     * Get.
     *
     * @param id int
     * @return value T
     */
    T get(int id);
}
