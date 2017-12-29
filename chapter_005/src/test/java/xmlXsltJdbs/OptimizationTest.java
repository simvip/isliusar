package xmlxsltjdbs;

import org.junit.Test;

/**
 * Created by Ivan Sliusar on 29.12.2017.
 * Red Line Soft corp.
 */
public class OptimizationTest {

    /**
     * Test 1.
     */
    @Test
    public void run() {
        Optimization app = new Optimization();
        app.setConnectionString("jdbc:sqlite:testBase.db");
        app.setAmountRecords(10);
        app.run();
    }

}