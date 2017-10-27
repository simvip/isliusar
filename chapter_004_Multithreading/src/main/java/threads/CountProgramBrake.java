package threads;

/**
 * Created by Ivan Sliusar on 25.10.2017.
 * Red Line Soft corp.
 */
public class CountProgramBrake {
    /**
     * Input string.
     */
    private String inputString;

    /**
     * Max milliseconds work.
     */
    private long maxMillisecondsWork;

    /**
     * Construct.
     *
     * @param inputString         String
     * @param maxMillisecondsWork long
     */
    public CountProgramBrake(String inputString, long maxMillisecondsWork) {
        this.inputString = inputString;
        this.maxMillisecondsWork = maxMillisecondsWork;
    }


    public void counting() {
        System.out.println("Начало работы программы");

        //подстчет символов
        Thread countThread = new Thread(
                new CountChar(this.inputString), "Подсчет символов");
        countThread.start();

        // контроль времени исполнения
        Thread controlThread = new Thread(
                new Time(countThread, this.maxMillisecondsWork), "Контроль времени выполнения");
        controlThread.start();

        try {
            controlThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Конец работы программы");
    }
}
