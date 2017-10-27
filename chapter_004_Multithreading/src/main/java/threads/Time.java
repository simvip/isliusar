package threads;

/**
 * Created by Ivan Sliusar on 25.10.2017.
 * Red Line Soft corp.
 */
public class Time implements Runnable {
    /**
     * Thread which need to monitor.
     */
    private Thread monitorThread;
    /**
     * Max milliseconds to work monitor thread.
     */
    private long maxOperationTimeMilli;

    /**
     * Truncated construct.
     *
     * @param monitorThread Thread
     */
    public Time(Thread monitorThread, long maxOperationTimeMilli) {
        this.monitorThread = monitorThread;
        this.maxOperationTimeMilli = maxOperationTimeMilli;
    }

    /**
     * Override method run.
     */
    @Override
    public void run() {
        System.out.format("Поток %s стартовал %n", Thread.currentThread().getName(), System.lineSeparator());

        try {
            this.monitorThread.join(this.maxOperationTimeMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitorThread.interrupt();

        System.out.format("Поток %s завершился %n", Thread.currentThread().getName(), System.lineSeparator());
    }
}
