package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 02.11.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class ProducerAndConsumer {

    /**
     * Lock
     */
    Object lock = new Object();

    /**
     * pull task.
     */
    @GuardedBy("lock")
    private BlockingQueue<String> queue = new BlockingQueue<>(4);

    /**
     * Work is done.
     */
    @GuardedBy("this")
    private boolean workIsDone = false;

    /**
     * capacity queue.
     */
    private int capacity;
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
        this.capacity = 4;
        queue = new BlockingQueue<>(this.capacity);
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

            while (!workIsDone) {
                if (queue.size() == 0){
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
            while (queue.size() == this.capacity) {
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

    private class BlockingQueue<T>{
        /**
         * Capacity
         */
        private int capacity;
        /**
         * Max index of array data.
         */
        private int maxIndex = -1;
        /**
         * array of values.
         */
        private Object data[];

        /**
         * Construct.
         * @param lenght
         */
        public BlockingQueue(int lenght) {
            this.capacity = lenght;
            data = new Object[lenght];
        }

        /**
         * Add value in queue.
         * @param value T
         */
        public void add(T value){
            synchronized (lock) {
                if (maxIndex <= capacity - 1) {
                    data[++maxIndex] = value;
                }
            }
        }

        /**
         * Poll value from queue.
         * @return T
         */
        public T poll(){
            synchronized (lock) {
                Object[] temp = new Object[this.data.length];
                System.arraycopy(this.data, 1, temp, 0, maxIndex);
                T returnvalue = (T) this.data[0];
                maxIndex--;
                this.data = temp;
                return returnvalue;
            }
        }

        /**
         * Size of queue.
         * @return int.
         */
        public int size(){
            return maxIndex+1;
        }
    }

}
