package ru.job4j.bigtask;

import ru.job4j.bigtask.model.Item;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 23.06.2017
 */
public class StartUi {
    /**
     * menu of our program.
     */
    private String[] doList;

    /**
     * Construct.
     */
    public StartUi() {
        createDoList();
    }

    /**
     * start of program.
     */
    public void start() {

        printList(true);

        Tracker tracker = new Tracker();
        ConsoleInput input = new ConsoleInput();
        boolean programOnline = true;
        Item[] items;
        int userAnswer;
        Item currentItem = null;

        while (programOnline) {

            userAnswer = Integer.parseInt(input.answer("Select"));

            switch (userAnswer) {
                case 0:
                    currentItem = new Item(
                            input.answer("Please input Name"),
                            input.answer("Please input description"),
                            Long.parseLong(input.answer("Please input create")));
                    tracker.add(currentItem);
                    break;
                case 1:
                    items = tracker.findAll();
                    for (Item item :
                            items) {
                        if (item == null) {
                            break;
                        }
                        System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), String.valueOf(item.getCreate()));
                    }
                    System.out.println();
                    break;
                case 2:
                    if (currentItem == null) {
                        currentItem = tracker.findById(input.answer("input Id Item for update"));
                    }
                    tracker.update(currentItem);
                    break;
                case 3:
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
                case 4:
                    currentItem = tracker.findById(input.answer("input Id for search"));
                    if (currentItem == null) {
                        System.out.println("find failed");

                    } else {
                        System.out.println("find successful");
                    }
                    break;
                case 5:
                    items = tracker.findByName(input.answer("input Name for search"));
                    System.out.println("I found the following list Item:");
                    for (Item item :
                            items) {
                        System.out.printf("ID:%s  NAME:%s  DESCRIPTION:%s  \n", item.getId(), item.getName(), item.getCreate());
                    }
                    System.out.println();
                    break;
                case 6:
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
     */
    private void printList(boolean programOnline) {
        if (programOnline) {
            for (int i = 0; i < doList.length; i++) {
                System.out.println("" + i + "." + doList[i]);
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
     * psvm.
     */
    public static void main(String[] args) {
        new StartUi().start();
    }
}


