package threads;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class Counting {
    /**
     * Counting words.
     *
     * @param inputString String
     */
    private void countingWords(final String inputString) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = 0;
                String[] split = inputString.split(" ");
                for (String value : split) {
                    max++;
                }
                System.out.println("Words in the sentence " + max);
            }
        }).start();
    }

    /**
     * Counting spaces.
     *
     * @param inputString String
     */
    private void countingSpace(final String inputString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = 0;
                String[] split = inputString.split(" ");
                for (String value : split) {
                    max++;
                }
                max--;
                System.out.println("Space in the sentence " + max);
            }
        }).start();
    }

    /**
     * Count.
     * @param inputString String
     */
    public void count(String inputString) {
        countingSpace(inputString);
        countingWords(inputString);
    }

}
