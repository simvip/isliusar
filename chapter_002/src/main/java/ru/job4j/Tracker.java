package ru.job4j;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.job4j.model.Item;

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
    private List<Item> items = new ArrayList<>();
    /**
     * max item.
     */
    private int counter = 0;
    /**
     * Id item.
     */
    private int idCount = 0;
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
        if (this.counter <= this.items.size()) {
            //item.setId(String.valueOf(System.currentTimeMillis() + "-" + RD.nextInt(100)));
            item.setId(String.valueOf(this.idCount++));
            items.add(item);
        }
        return item;
    }

    /**
     * update item in storage.
     *
     * @param item Item
     */
    public void update(Item item) {
        this.items.add(getIdexInStorage(item), item);
    }

    /**
     * delete item in srorage.
     *
     * @param item Item
     */
    public void delete(Item item) {
        int itemIndex = getIdexInStorage(item);
        items.remove(itemIndex);
    }

    /**
     * find all items.
     *
     * @return Item
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * find item in storage by key.
     *
     * @param key String.
     * @return Item
     */
    public List<Item> findByName(String key) {

        List<Item> newItem = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                newItem.add(item);

            }
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
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                return item;
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

        String itemId = item.getId();

        int sizeList = this.items.size();
        for (int i = 0; i < sizeList; i++) {
            Item currentitem = this.items.get(i);
            if (currentitem != null && currentitem.getId().equals(itemId)) {
                return i;
            }
        }
        return 0;
    }
}
