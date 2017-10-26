package threads;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

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
        String inputString = "200 тысяч лет назад человекообразное существо, известное под именем Homo erectus (Человек прямостоящий), внезапно превратилось в Homo sapiens (Человека разумного). При этом объем головного мозга у него увеличился на 50 %, он обрел способность говорить, и его анатомическое строение приблизилось к анатомическому строению современного человека. Спрашивается, как это могло произойти так внезапно после 1,2 миллиона лет полного отсутствия прогресса? Именно странности такого рода ставили в весьма затруднительное положение чрезвычайно уважаемых ученых-эволюционистов — таких как Ноам Хомски и Роджер Пенроуз. Когда мы применяем систему эволюционных принципов к Homo sapiens, то единственный логический вывод состоит в том, что человечество не могло бы существовать.";
        System.out.println("Start calculating");

        Thread count = new Counting(inputString);
        count.start();

        try {
            Thread.sleep(1000);
            count.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            count.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("End calculating");
    }
}