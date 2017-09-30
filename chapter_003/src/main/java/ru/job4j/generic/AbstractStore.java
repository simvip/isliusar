package ru.job4j.generic;


/**
 * Created by Ivan Sliusar on 18.09.2017.
 * Red Line Soft corp.
 * @param <V>
 */
public abstract class AbstractStore<V extends Base> implements Store<V> {
    /**
     * Array storage.
     */
    private SimpleArray<V> storage;
    /**
     * Current index in storage.
     */
    private int indexInStorage;

    /**
     * Construct.
     *
     * @param sizeStorage int
     */
    public AbstractStore(int sizeStorage) {
        this.storage = new SimpleArray<>(sizeStorage);
        this.indexInStorage = 0;
    }

    /**
     * Add.
     *
     * @param value T
     */
    @Override
    public void add(V value) {
        value.setId(String.valueOf(indexInStorage));
        storage.add(value);
    }

    /**
     * Delete.
     *
     * @param value T
     */
    @Override
    public void delete(V value) {
        storage.delete(value);
    }

    /**
     * Update.
     *
     * @param value T
     */
    @Override
    public void update(V value) {
        storage.update(Integer.valueOf(value.getId()), value);
    }

    /**
     * Get.
     *
     * @param id int
     * @return Base
     */
    @Override
    public V get(int id) {
        return storage.get(id);
    }
}

