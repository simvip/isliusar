package ru.job4j.strategy;

/**
 * Class Square.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 29.06.2017
 */
public class Square implements Shape {
    /**
     *Pic.
     *
     * @return String
     */
    @Override
    public String pic() {
        String line = "* * * *";
        String separator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(line);
            if (i < 3) {
                builder.append(separator);
            }
        }
        return builder.toString();
    }
}
