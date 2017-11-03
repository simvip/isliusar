package wait;

/**
 * Created by Ivan Sliusar on 03.11.2017.
 * Red Line Soft corp.
 */
public class Work implements Runnable {
    /**
     * Name of work.
     */
    private String nameOfWork;

    public Work(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }

    /**
     * Override run.
     */
    @Override
    public void run() {
        System.out.println("Work " + this.nameOfWork + " start");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter.
     * @return String
     */
    public String getNameOfWork() {
        return nameOfWork;
    }
}
