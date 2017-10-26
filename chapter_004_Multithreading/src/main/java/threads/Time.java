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
     * Construct with all parameters.
     *
     * @param monitorThread         Thread
     * @param maxOperationTimeMilli long
     */
    public Time(Thread monitorThread, long maxOperationTimeMilli) {
        this.monitorThread = monitorThread;
        this.maxOperationTimeMilli = Math.max(maxOperationTimeMilli, 10);
    }

    /**
     * Truncated construct.
     *
     * @param monitorThread Thread
     */
    public Time(Thread monitorThread) {
        this.monitorThread = monitorThread;
        this.maxOperationTimeMilli = 10;
    }

    /**
     * Override method run.
     */
    @Override
    public void run() {

        try {
            while (!monitorThread.isInterrupted() && this.maxOperationTimeMilli >= 0) {
                Thread.sleep(10);
                this.maxOperationTimeMilli += -10;
            }

            if (!monitorThread.isInterrupted()) {
                monitorThread.interrupt();
                throw new InterruptedException();
            }

        } catch (InterruptedException e) {
            System.out.println("----------------------");
        }
    }
}
