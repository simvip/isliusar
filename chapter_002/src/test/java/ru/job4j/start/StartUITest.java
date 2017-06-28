package ru.job4j.start;

import org.junit.Test;
import ru.job4j.Input;
import ru.job4j.StubInput;
import ru.job4j.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 27.06.2017
 */
public class StartUITest {
    /**
     * Test ADD.
     */
    @Test
    public void whenAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "12", "6", "yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Test Show all.
     */
    @Test
    public void whenShowAllThenPrintAllItemList() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "12", "0", "test name2", "desc", "12", "6", "yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Test edit item.
     */
    @Test
    public void whenEditItemThenTrackerHasEditItemIn() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "12", "2", "New name", "6", "yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("New name"));
    }

    /**
     * Test delete item.
     */
    @Test
    public void whenDeleteItemThenTrackerHasNoThisItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0", "test name", "desc", "12",
                "0", "test name2", "desc2", "13",
                "0", "test name3", "desc3", "14",
                "3",
                "6", "yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test name3").length, is(0));
    }

    /**
     * Test find item by id.
     */
    @Test
    public void whenFindByIdThenReturnItemWithInputId() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0", "test name", "desc", "12",
                "0", "test name2", "desc2", "13",
                "0", "test name3", "desc3", "14",
                "6", "yes"});
        new StartUI(input, tracker).init();
        String idForSearch = tracker.findByName("test name2")[0].getId();
        assertThat(tracker.findById(idForSearch).getName(), is("test name2"));
    }

    /**
     * Test find item by name.
     */
    @Test
    public void whenFindByIdThenReturnAllItemWithInputName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0", "test name", "desc", "12",
                "0", "test name2", "desc2", "13",
                "0", "test name3", "desc3", "14",
                "6", "yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test name2")[0].getName(), is("test name2"));
    }

    /**
     * Exit program.
     */
    @Test
    public void whenExitProgramThen() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0", "test name", "desc", "12",
                "6", "yes"});
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(startUI.isProgramOnline(), is(false));
    }
}