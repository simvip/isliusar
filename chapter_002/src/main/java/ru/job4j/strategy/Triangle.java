package ru.job4j.strategy;

/**
 * Class Triangle.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 29.06.2017
 */
public class Triangle implements Shape {
    @Override
    public String pic() {
        return String.format("  x  %s ххх %<sxxxxx",System.getProperty("line.separator")).toString();
    }
}
