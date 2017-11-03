package wait;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 03.11.2017.
 * Red Line Soft corp.
 */
public class ThreadPoolTest {
    /**
     * Test.
     */
    @Test
    public void endOfWork() {

        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.add(new Work("multiplication " + i));
        }
        threadPool.endOfWork();
    }

}