package threads;

import org.junit.Test;

/**
 * Created by Ivan Sliusar on 25.10.2017.
 * Red Line Soft corp.
 */
public class CountProgramBrakeTest {

    /**
     * Test 1.
     */
    @Test
    public void testProgramBreadThreads() {
        long maxMillisecondsWork = 100;
        String inputString = "ОРД – вид деятельности, осуществляемой гласно и негласно оперативными подразделениями государственных органов, уполномоченных на то Законом об ОРД, в пределах их полномочий посредством проведения ОРМ в целях защиты жизни, здоровья, прав и свобод человека и гражданина, собственности, обеспечения безопасности общества и государства от преступных посягательств (ст. 1 Закона).\n";

        new CountProgramBrake(inputString, maxMillisecondsWork);
    }
}