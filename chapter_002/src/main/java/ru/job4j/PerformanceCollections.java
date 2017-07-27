package ru.job4j;


import java.util.Collection;

/**
 * Класс для тестированния произовдительности коллекций
 */
public class PerformanceCollections {
    /**
     * Add to collection.
     *
     * @param collection Test Collection
     * @param amount     int amount elements need add to collection
     * @return
     */
    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < amount; i++) {
            collection.add("test".concat(String.valueOf(i)));
        }

        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }

    /**
     * Delete from list.
     *
     * @param collection Test Collection
     * @param amount     int amount elements need delete from collection
     * @return
     */
    public long delete(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < amount; i++) {
            collection.remove("test".concat(String.valueOf(i)));
        }

        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }
}
