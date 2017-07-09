package ru.job4j;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 09.07.2017
 */
public class MenuOutException extends RuntimeException {
    /**
     * construct.
     * @param message String
     */
    public MenuOutException(String message) {
        super(message);
    }
}
