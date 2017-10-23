package threads;

import org.junit.Test;

/**
 * Created by Ivan Sliusar on 23.10.2017.
 * Red Line Soft corp.
 */
public class CountingTest {
    /**
     * Test 1.
     */
    @Test
    public void countingWords() {
        Counting counting = new Counting();
        counting.count("на дворе дрова на дровах братва");
    }
}