package ru.job4j;

/**
 * Class ConsoleInput.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 27.06.2017
 */
public class StubInput implements Input {
    /**
     * massive answers.
     */
    private String[] answers;
    /**
     * index.
     */
    private int index = 0;

    /**
     * Construct.
     * @param answers String[]
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * answer for question.
     * @param question String
     * @return String
     */
    public String answer(String question) {
        System.out.println(question);
        return answers[index++];
    }

    /**
     * validate answer.
     * @param question String.
     * @param range int
     * @return int
     */
    @Override
    public int answer(String question, int range) {
        System.out.println(question);
        return Integer.valueOf(answers[index++]);
    }
}
