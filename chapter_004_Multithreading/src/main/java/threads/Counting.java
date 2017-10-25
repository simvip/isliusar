package threads;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class Counting implements Runnable {
    /**
     * Input string.
     */
    private String inputString;

    /**
     * Construct.
     *
     * @param inputString String
     */
    public Counting(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        int max = 0;
        String[] split = inputString.split(" ");

        try {
            for (String value : split) {

                if (!Thread.currentThread().isInterrupted()) {
                    max++;
                } else {
                    throw new InterruptedException();
                }
            }

            System.out.println("Words in the sentence " + max);
            max--;
            System.out.println("Space in the sentence " + max);

        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
        }

    }
}
