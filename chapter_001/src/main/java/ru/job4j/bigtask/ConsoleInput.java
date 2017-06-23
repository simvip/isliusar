package ru.job4j.bigtask;

import java.util.Scanner;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 23.06.2017
 */
public class ConsoleInput {
    Scanner scanner = new Scanner(System.in);
    public String answer(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
