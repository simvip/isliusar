package jmm;

/**
 * Created by Ivan Sliusar on 30.10.2017.
 * Red Line Soft corp.
 */
public class ExampleThrowable implements Runnable {
    private final int idThread;
    /**
     * Common variable.
     */
    private static int commonVariable = 0;

    /**
     * Construct.
     *
     * @param idThread
     */
    public ExampleThrowable(int idThread) {
        this.idThread = idThread;
    }

    /**
     * Getter.
     *
     * @return int
     */
    public static int getCommonVariable() {
        return commonVariable;
    }

    /**
     * Setter.
     */
    public static void setCommonVariable() {
        ExampleThrowable.commonVariable++;
        Thread.yield();
        ExampleThrowable.commonVariable++;
    }

    /**
     * Override run.
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            setCommonVariable();
            System.out.printf("Thread %s value = %s %n", Thread.currentThread().getName(), getCommonVariable(), System.lineSeparator());
        }
    }
}
