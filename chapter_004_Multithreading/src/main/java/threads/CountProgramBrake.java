package threads;

/**
 * Created by Ivan Sliusar on 25.10.2017.
 * Red Line Soft corp.
 */
public class CountProgramBrake {

    /**
     * Construct.
     *
     * @param inputString String
     */
    public CountProgramBrake(String inputString, long maxMillisecondsWork) {

        Thread countThread = new Thread(new CountChar(inputString));
        countThread.start();

        Thread controlThread = new Thread(new Time(countThread, maxMillisecondsWork));
        controlThread.start();

        try {
            countThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            controlThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
