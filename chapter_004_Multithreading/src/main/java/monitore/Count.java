package monitore;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by Ivan Sliusar on 30.10.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class Count {
    /**
     * Lock monitor.
     */
    private Object lock = new Object();
    /**
     * Value.
     */
    @GuardedBy("lock")
    private int value = 0;

    /**
     * Incremant.
     *
     * @return int
     */
    public int incremant() {
        synchronized (lock) {
            return value++;
        }
    }

    /**
     * Getter.
     *
     * @return int
     */
    public int getValue() {
        synchronized (lock) {
            return value;
        }
    }
}
