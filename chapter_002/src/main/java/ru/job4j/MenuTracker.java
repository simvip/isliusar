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
class FindByName implements UserAction {
    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execute(Input input, Tracker tracker) {

        Item[] items = tracker.findByName(input.answer("input Name for search"));
        System.out.println("I found the following list Item:");
        for (Item item : items) {
            System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), item.getCreate());
        }
        System.out.println();
    }

    @Override
    public String info() {
        return "Find item by name";
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
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowAll();
        this.actions[2] = new EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new FindById();
        this.actions[5] = new FindByName();
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
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            tracker.add(new Item(
                    input.answer("Please input Name"),
                    input.answer("Please input description"),
                    Long.parseLong(input.answer("Please input create")))
            );
        }

        @Override
        public String info() {
            return "Add new item";
        }
    }

    /**
     * Show.
     */
    private class ShowAll implements UserAction {
        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return "Show all items";
        }
    }

    /**
     * Edit.
     */
    private class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            Item currentItem = tracker.findById(input.answer("input Id Item for update"));

            currentItem.setName(input.answer("input New name for update"));
            tracker.update(currentItem);
        }

        @Override
        public String info() {
            return "Edit item";
        }
    }

    /**
     * Delete.
     */
    private static class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

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

        @Override
        public String info() {
            return "Delete item";
        }
    }

    /**
     * FindById.
     */
    private class FindById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            Item currentItem = tracker.findById(input.answer("input Id for search"));
            if (currentItem == null) {
                System.out.println("find failed");

            } else {
                System.out.println("find successful");
            }
        }

        @Override
        public String info() {
            return "Find item by id";
        }
    }

}
