package threads;

/**
 * Created by Ivan Sliusar on 25.10.2017.
 * Red Line Soft corp.
 */
public class CountChar implements Runnable {
    /**
     * Input string.
     */
    private String inputString;

    /**
     * Construct.
     *
     * @param inputString String
     */
    public CountChar(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Construct no parameters.
     */
    public CountChar() {
        this.inputString = "200 тысяч лет назад человекообразное существо, известное под именем Homo erectus (Человек прямостоящий), внезапно превратилось в Homo sapiens (Человека разумного). При этом объем головного мозга у него увеличился на 50 %, он обрел способность говорить, и его анатомическое строение приблизилось к анатомическому строению современного человека. Спрашивается, как это могло произойти так внезапно после 1,2 миллиона лет полного отсутствия прогресса? Именно странности такого рода ставили в весьма затруднительное положение чрезвычайно уважаемых ученых-эволюционистов — таких как Ноам Хомски и Роджер Пенроуз. Когда мы применяем систему эволюционных принципов к Homo sapiens, то единственный логический вывод состоит в том, что человечество не могло бы существовать.";
    }

    /**
     * Override method run.
     */
    @Override
    public void run() {
        int max = 0;
        String[] split = this.inputString.split(" ");

        try {
            Thread.sleep(1000);
            for (String value : split) {
                if (!Thread.currentThread().isInterrupted()) {
                    max++;
                } else {
                    throw new InterruptedException();
                }
            }
            System.out.println("Words in the sentence " + max);
            max--;
            System.out.println("Space in the sentence " + max);
        } catch (InterruptedException e) {
            System.out.println("Thread Count is interrupted");
        }
        System.out.println("Thread Count is end of work");
    }
}
