package ru.job4j;

import ru.job4j.model.Item;

/**
 * Class FindByName.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 04.07.2017
 */
class FindByName extends BaseAction {

    @Override
    public void execute(Input input, Tracker tracker) {

        Item[] items = tracker.findByName(input.answer("input Name for search"));
        System.out.println("I found the following list Item:");
        for (Item item : items) {
            System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), item.getCreate());
        }
        System.out.println();
    }

    /**
     * construct.
     *
     * @param key  String
     * @param name string
     */
    FindByName(String key, String name) {
        super(key, name);
    }
}

/**
 * Class MenuTracker.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 04.07.2017
 */
public class MenuTracker {
    /**
     * input.
     */
    private Input input;
    /**
     * tracker.
     */
    private Tracker tracker;
    /**
     * pull actions.
     */
    private UserAction[] actions = new UserAction[6];

    /**
     * construct.
     *
     * @param input   Input
     * @param tracker Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        fillActions();
    }

    /**
     * Print menu.
     */
    public void printMenu() {
        for (UserAction action : this.actions) {
            System.out.printf("%s. %s \n", action.key(), action.info());
        }
        System.out.println("6. Exit");
        System.out.println("--------------------");
    }

    /**
     * Fill actions.
     */
    private void fillActions() {
        this.actions[0] = new AddItem("0", "Add item");
        this.actions[1] = new ShowAll("1", "Show all");
        this.actions[2] = new EditItem("2", "Edit item");
        this.actions[3] = new MenuTracker.DeleteItem("3", "Delete item");
        this.actions[4] = new FindById("4", "Finde by id");
        this.actions[5] = new FindByName("5", "Finde by name");
    }

    /**
     * do select.
     *
     * @param key int
     */
    public void select(int key) {
        if (key >= 0 & key <= 5) {
            actions[key].execute(this.input, this.tracker);
        }
    }

    /**
     * Add.
     */
    private class AddItem extends BaseAction {
        /**
         * construct.
         *
         * @param key  String
         * @param name string
         */
        AddItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            tracker.add(new Item(
                    input.answer("Please input Name"),
                    input.answer("Please input description"),
                    Long.parseLong(input.answer("Please input create")))
            );
        }

    }

    /**
     * Show.
     */
    private class ShowAll extends BaseAction {

        /**
         * construct.
         *
         * @param key  String
         * @param name string
         */
        ShowAll(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            Item[] items = tracker.findAll();
            for (Item item : items) {
                if (item == null) {
                    break;
                }
                System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), String.valueOf(item.getCreate()));
            }
            System.out.println();
        }
    }

    /**
     * Edit.
     */
    private class EditItem extends BaseAction {

        @Override
        public void execute(Input input, Tracker tracker) {

            Item currentItem = tracker.findById(input.answer("input Id Item for update"));

            currentItem.setName(input.answer("input New name for update"));
            tracker.update(currentItem);
        }

        /**
         * construct.
         *
         * @param key  String
         * @param name string
         */
        EditItem(String key, String name) {
            super(key, name);
        }
    }

    /**
     * Delete.
     */
    private static class DeleteItem extends BaseAction {

        @Override
        public void execute(Input input, Tracker tracker) {

            Item currentItem = tracker.findById(input.answer("input Id Item for delete"));

            tracker.delete(currentItem);
            if (tracker.findById(currentItem.getId()) == null) {
                System.out.println("delete successful");
            } else {
                System.out.println("delete failed");
            }
        }

        /**
         * construct.
         *
         * @param key  String
         * @param name string
         */
        DeleteItem(String key, String name) {
            super(key, name);
        }
    }

    /**
     * FindById.
     */
    private class FindById extends BaseAction {

        @Override
        public void execute(Input input, Tracker tracker) {

            Item currentItem = tracker.findById(input.answer("input Id for search"));
            if (currentItem == null) {
                System.out.println("find failed");

            } else {
                System.out.println("find successful");
            }
        }

        /**
         * construct.
         *
         * @param key  String
         * @param name string
         */
        FindById(String key, String name) {
            super(key, name);
        }
    }

}
