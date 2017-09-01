package ru.job4j;


import org.junit.Test;
import ru.job4j.model.Item;

import static org.hamcrest.core.Is.is;

import org.hamcrest.core.IsNull;

import static org.junit.Assert.assertThat;

/**
 * Class TeacherTest.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 22.06.2017
 */
public class TrackerTest {
    /**
     * test addNew.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * test update.
     */
    @Test
    public void whenUpdateItemThenTrackerSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        tracker.update(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * test delete.
     */
    @Test
    public void whenDeleteItemThenTrackerFindeItemNull() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        tracker.delete(item2);

        assertThat(tracker.findById(item2.getId()), is(IsNull.nullValue()));
    }

    /**
     * test findAll.
     */
    @Test
    public void whenFindAllItemsThenTrackerSameItems() {
        Item[] items = new Item[2];
        Tracker tracker = new Tracker();

        Item item1 = new Item("test1", "testDescription", 123L);
        items[0] = item1;
        tracker.add(item1);

        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        items[1] = item2;

        assertThat(tracker.findAll(), is(items));
    }

    /**
     * test findByName.
     */
    @Test
    public void whenFindByNameItemNameThenTrackerItemsWithSameName() {
        Item[] itemsExpected = new Item[1];
        Tracker tracker = new Tracker();

        Item item1 = new Item("test1", "testDescription", 123L);
        itemsExpected[0] = item1;
        tracker.add(item1);

        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);

        assertThat(tracker.findByName(item1.getName()), is(itemsExpected));
    }

    /**
     * test findByName.
     */
    @Test
    public void whenFindByNameItemNameThenTrackerItemsWithSameName2() {
        Item[] itemsExpected = new Item[0];
        Tracker tracker = new Tracker();

        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);

        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);

        assertThat(tracker.findByName("test3"), is(itemsExpected));
    }

    /**
     * test findById.
     */
    @Test
    public void whenFindByIdItemIdThenThrakerItemsWithSameId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        assertThat(tracker.findById(item.getId()), is(item));
    }

}