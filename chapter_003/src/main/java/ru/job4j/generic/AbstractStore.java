package ru.job4j.generic;


/**
 * Created by Ivan Sliusar on 18.09.2017.
 * Red Line Soft corp.
 */
public abstract class AbstractStore implements Store {
    /**
     * Array storage.
     */
    private SimpleArray<Base> storage;
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
     * Add
     *
     * @param value T
     */
    @Override
    public void add(Base value) {
        value.setId(String.valueOf(indexInStorage));
        storage.add(value);
    }

    /**
     * Delete.
     *
     * @param value T
     */
    @Override
    public void delete(Base value) {
        storage.delete(value);
    }

    /**
     * Update.
     *
     * @param value T
     */
    @Override
    public void update(Base value) {
        storage.update(Integer.valueOf(value.getId()), value);
    }

    /**
     * Get.
     *
     * @param id int
     * @return Base
     */
    @Override
    public Base get(int id) {
        return storage.get(id);
    }
}

