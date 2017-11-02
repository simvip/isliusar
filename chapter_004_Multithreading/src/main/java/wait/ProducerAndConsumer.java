package wait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Ivan Sliusar on 02.11.2017.
 * Red Line Soft corp.
 */
public class ProducerAndConsumer {

    /**
     * Lock
     */
    Object lock = new Object();

    /**
     * pull task.
     */
    private Queue<String> queue = new LinkedList<>();

    /**
     * Work is done.
     */
    private boolean workIsDone = false;

    /**
     * capacity queue.
     */
    private int capacity = 4;
    /**
     * Input string.
     */
    private String inputString;

    /**
     * Construct.
     *
     * @param inputString String
     */
    public ProducerAndConsumer(String inputString) {
        this.inputString = inputString;

    }

    /**
     * Start Program.
     */
    public void startProgram() {
        List<Thread> threadList = new ArrayList<>();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] arrayString = inputString.split(" ");
                for (String item : arrayString) {
                    produceTask(item);
                }

                endWork();
            }
        }, "Producer");

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                consumerTask();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * End of work.
     */
    private void endWork() {

        synchronized (this.lock) {
            System.out.println("End of program");
            this.workIsDone = true;
        }
    }

    /**
     * Consumer.
     */
    private void consumerTask() {

        synchronized (this.lock) {
            while (true) {
                if (queue.size() == 0) {
                    if (this.workIsDone) {
                        break;
                    }
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String value = queue.poll();
                System.out.println(" Consume task " + value);
                this.lock.notifyAll();
            }
            System.out.println("CONSUMER End Work");
        }

    }

    /**
     * Producer
     *
     * @param task String
     */
    private void produceTask(String task) {

        synchronized (this.lock) {
            if (queue.size() == this.capacity) {
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(task);
            System.out.println("Produce task " + task);
            this.lock.notifyAll();
        }
    }

}
