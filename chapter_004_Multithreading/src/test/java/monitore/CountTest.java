package monitore;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 30.10.2017.
 * Red Line Soft corp.
 */
public class CountTest {

    /**
     * Test.
     */
    @Test
    public void incremant() {
        Count count = new Count();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        try {
                            Thread.sleep((long) Math.random() * 2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count.incremant();
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertThat(count.getValue(), is(25));
    }

}