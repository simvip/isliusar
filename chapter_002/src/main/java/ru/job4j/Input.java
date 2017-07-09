package ru.job4j;

/**
 * Class Tracker.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 28.06.2017
 */
public interface Input {
    /**
     * answer.
     * @param question String
     * @return String
     */
    String answer(String question);

    /**
     * validate answer.
     * @param question String
     * @param range int
     * @return int
     */
    int answer(String question, int range);
}
