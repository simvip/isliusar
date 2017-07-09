package ru.job4j.start;

import ru.job4j.Input;
import ru.job4j.MenuTracker;
import ru.job4j.Tracker;
import ru.job4j.ValidateInput;
import ru.job4j.model.Item;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 23.06.2017
 */
    public class StartUI {
    /**
     * Add.
     */
    private static final int ADD = 0;
    /**
     * Show all.
     */
    private static final int SHOW_ALL = 1;
    /**
     * Edit.
     */
    private static final int EDIT = 2;
    /**
     * delete.
     */
    private static final int DELETE = 3;
    /**
     * find by id.
     */
    private static final int FIND_BY_ID = 4;
    /**
     * finde by name.
     */
    private static final int FIND_BY_NAME = 5;
    /**
     * exit.
     */
    private static final int EXIT = 6;

    /**
     * program Online.
     */
    private boolean programOnline;
    /**
     * menu of our program.
     */
    private String[] doList;

    /**
     * Input.
     */
    private Input input;

    /**
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Construct.
     */
    public StartUI() {
        createDoList();
        //this.input = new ConsoleInput();
        this.input = new ValidateInput();
        this.tracker = new Tracker();
    }

    /**
     * Other construct.
     *
     * @param input   Input
     * @param tracker Tracker
     */
    public StartUI(Input input, Tracker tracker) {
        createDoList();
        this.input = input;
        this.tracker = tracker;
        this.programOnline = true;
    }

    /**
     * start of program.
     */
    public void start() {

        printList(true);

        Item[] items;
        int userAnswer;
        Item currentItem = null;

        while (programOnline) {

            userAnswer = Integer.parseInt(input.answer("Select"));

            switch (userAnswer) {
                case ADD:
                    currentItem = new Item(
                            input.answer("Please input Name"),
                            input.answer("Please input description"),
                            Long.parseLong(input.answer("Please input create")));
                    tracker.add(currentItem);
                    break;
                case SHOW_ALL:
                    items = tracker.findAll();
                    for (Item item : items) {
                        if (item == null) {
                            break;
                        }
                        System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), String.valueOf(item.getCreate()));
                    }
                    System.out.println();
                    break;
                case EDIT:
                    if (currentItem == null) {
                        currentItem = tracker.findById(input.answer("input Id Item for update"));
                    }
                    currentItem.setName(input.answer("input New name for update"));
                    tracker.update(currentItem);
                    break;
                case DELETE:
                    if (currentItem == null) {
                        currentItem = tracker.findById(input.answer("input Id Item for delete"));
                    }
                    tracker.delete(currentItem);
                    if (tracker.findById(currentItem.getId()) == null) {
                        System.out.println("delete successful");
                    } else {
                        System.out.println("delete failed");
                    }
                    break;
                case FIND_BY_ID:
                    currentItem = tracker.findById(input.answer("input Id for search"));
                    if (currentItem == null) {
                        System.out.println("find failed");

                    } else {
                        System.out.println("find successful");
                    }
                    break;
                case FIND_BY_NAME:
                    items = tracker.findByName(input.answer("input Name for search"));
                    System.out.println("I found the following list Item:");
                    for (Item item : items) {
                        System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), item.getCreate());
                    }
                    System.out.println();
                    break;
                case EXIT:
                    String answer = input.answer("You want to close program? yes/no");
                    programOnline = !answer.equals("yes");
                    break;
                default:
                    System.out.println("Действие не предустмотренно");
                    break;
            }
            printList(programOnline);
        }

    }

    /**
     * Print menu of program.
     *
     * @param programOnline boolean
     */
    private void printList(boolean programOnline) {
        if (programOnline) {
            for (int i = 0; i < doList.length; i++) {
                System.out.printf("%d. %s \n", i, doList[i]);
            }
        } else {
            System.out.println("Bye bye");
        }
    }

    /**
     * Create menu of program.
     */
    private void createDoList() {
        doList = new String[7];
        doList[0] = "Add new item";
        doList[1] = "Show all items";
        doList[2] = "Edit item";
        doList[3] = "Delete item";
        doList[4] = "Find item by id";
        doList[5] = "Find items by name";
        doList[6] = "Exit Program";
    }

    /**
     * Status program.
     *
     * @return boolean
     */
    public boolean isProgramOnline() {
        return programOnline;
    }

    /**
     * Init Start.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        int keyAnswer = 6;
        do {
            menuTracker.printMenu();
            keyAnswer = input.answer("Select", doList.length);
            menuTracker.select(keyAnswer);
        } while (keyAnswer != 6);
        this.programOnline = false;
    }

    /**
     * psvm.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new StartUI().init();
    }
}


