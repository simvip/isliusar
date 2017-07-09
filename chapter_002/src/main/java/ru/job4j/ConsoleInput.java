package ru.job4j;

import java.util.Scanner;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 23.06.2017
 */
public class ConsoleInput implements Input {
    /**
     * scanner system in.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Answer users.
     * @param question String
     * @return String
     */
    public String answer(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int answer(String question, int range) {
        int keyAnswer = Integer.parseInt(answer(question));

        if (!(keyAnswer < range && keyAnswer >= 0)) {
            throw new MenuOutException("Action with this key not find, please choose correct key");
        }
        return keyAnswer;
    }
}
