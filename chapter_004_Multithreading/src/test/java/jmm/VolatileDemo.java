package jmm;

/**
 * Created by Ivan Sliusar on 30.10.2017.
 * Red Line Soft corp.
 */
public class VolatileDemo {
    /**
     * Common variable.
     */
    private boolean stopAllThreads = false;

    /**
     * Start work
     */
    public void start() {
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Start timer");
                try {
                    Thread.sleep(1000);
                    stopAllThreads = true;
                    System.out.println("Time is over, all threads need stop!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int max = 0;
                    System.out.printf("Thread %s Start %n", Thread.currentThread().getName(), System.lineSeparator());
                    while (!stopAllThreads) {
                        max++;
                    }
                    System.out.printf("Thread %s End work %n", Thread.currentThread().getName(), System.lineSeparator());
                }
            }, "# " + i).start();
        }

        timer.start();
        try {
            timer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
