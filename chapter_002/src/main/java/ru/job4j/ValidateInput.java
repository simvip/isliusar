package ru.job4j;

/**
 * Created by Admin on 09.07.2017.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * validate answer.
     *
     * @param question String
     * @param range    int
     * @return int
     */
    @Override
    public int answer(String question, int range) {
        boolean correctData = false;
        int keyAnswer = -1;
        do {
            try {
                keyAnswer = super.answer(question, range);
                correctData = true;
            } catch (MenuOutException moe) {
                System.out.println("Out of range menu");
            } catch (NumberFormatException nfe) {
                System.out.println("You mistake with choose key, please try again");
            }
        } while (!correctData);

        return keyAnswer;
    }
}
