package threads;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class Counting extends Thread {
    /**
     * Input String.
     */
    private String inputString;

    /**
     * Counting words.
     */
    private void countingWords() throws InterruptedException {
        int max = 0;
        String[] split = this.inputString.split(" ");
        for (String value : split) {
            if (!isInterrupted()) {
                max++;
            } else {
                throw new InterruptedException();
            }

        }
        System.out.println("Words in the sentence " + max);
    }

    /**
     * Counting spaces.
     */
    private void countingSpace() throws InterruptedException {

        int max = 0;
        String[] split = this.inputString.split(" ");
        for (String value : split) {
            if (!isInterrupted()) {
                max++;
            } else {
                throw new InterruptedException();
            }
        }
        max--;
        System.out.println("Space in the sentence " + max);
    }

    /**
     * Run.
     */

    @Override
    public void run() {
        try {
            countingSpace();
            countingWords();
        } catch (InterruptedException e) {
            System.out.println("Interrupt count");
        }
    }

    /**
     * Construct.
     *
     * @param inputString
     */
    public Counting(String inputString) {
        this.inputString = inputString;
    }
}
