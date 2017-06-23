package ru.job4j.bigtask;

import ru.job4j.bigtask.model.Item;

import java.util.Random;

/**
 * Class Tracker.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Tracker {
    /**
     * items.
     */
    private Item[] items = new Item[100];
    /**
     * max item.
     */
    private int counter = 0;
    /**
     * RD.
     */
    private static final Random RD = new Random();

    /**
     * add in storage.
     *
     * @param item Item
     * @return item
     */
    public Item add(Item item) {
        if (this.counter <= this.items.length) {
            item.setId(String.valueOf(System.currentTimeMillis() + "-" + RD.nextInt(100)));
            items[this.counter++] = item;
        }
        return item;
    }

    /**
     * update item in storage.
     *
     * @param item Item
     */
    public void update(Item item) {
        this.items[getIdexInStorage(item)] = item;
    }

    /**
     * delete item in srorage.
     *
     * @param item Item
     */
    public void delete(Item item) {
        int itemIndex = getIdexInStorage(item);
        System.arraycopy(this.items, itemIndex + 1, this.items, itemIndex, this.counter - itemIndex);
    }

    /**
     * find all items.
     *
     * @return Item
     */
    public Item[] findAll() {
        Item[] newItem = new Item[this.counter];
        for (int i = 0; i < this.counter; i++) {
            newItem[i] = this.items[i];
        }
        return newItem;
    }

    /**
     * find item in storage by key.
     *
     * @param key String.
     * @return Item
     */
    public Item[] findByName(String key) {
        int currentCount = 0;
        Item[] newItem = new Item[this.counter];
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                newItem[currentCount] = item;
                currentCount++;
            }
        }

        if (newItem[0] != null) {
            Item[] trimmItems = new Item[currentCount];
            System.arraycopy(newItem, 0, trimmItems, 0, currentCount);
            newItem = trimmItems;
        }
        return newItem;
    }

    /**
     * find item in storage by id.
     *
     * @param id String
     * @return Item
     */
    public Item findById(String id) {
        int lenghtStorage = items.length;
        for (int index = 0; index < lenghtStorage; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                return this.items[index];
            }
        }
        return null;
    }

    /**
     * find item in storage, and return his index.
     *
     * @param item Item
     * @return int
     */
    private int getIdexInStorage(Item item) {
        int findIndex = 0;
        int lenghtStorage = items.length;
        String itemId = item.getId();

        for (int index = 0; index < lenghtStorage; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(itemId)) {
                findIndex = index;
                break;
            }
        }
        return findIndex;
    }
}
