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
    private void countingWords(){
        int max = 0;
        String[] split = this.inputString.split(" ");
        for (String value : split) {
            if (!Thread.currentThread().isInterrupted()) {
                max++;
            } else {
                break;
            }
        }
        System.out.println("Words in the sentence " + max);
    }

    /**
     * Counting spaces.
     */
    private void countingSpace(){

        int max = 0;
        String[] split = this.inputString.split(" ");
        for (String value : split) {
            if (!Thread.currentThread().isInterrupted()) {
                max++;
            } else {
                break;
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

        Thread threadW = new Thread(new Runnable() {
            @Override
            public void run() {
                countingWords();
            }
        });
        threadW.start();

        Thread threadS = new Thread(new Runnable() {
            @Override
            public void run() {
                countingSpace();
            }
        });
        threadS.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadW.interrupt();
        threadS.interrupt();
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
