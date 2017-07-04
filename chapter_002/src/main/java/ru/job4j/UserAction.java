package ru.job4j;

/**
 * Interface UserAction.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 04.07.2017
 */
public interface UserAction {
    /**
     * key.
     *
     * @return int
     */
    int key();

    /**
     * execute.
     *
     * @param input   Input
     * @param tracker Tracker
     */
    void execute(Input input, Tracker tracker);

    /**
     * Info.
     *
     * @return String
     */
    String info();
}
