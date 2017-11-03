package wait;

import java.util.*;

/**
 * Created by Ivan Sliusar on 03.11.2017.
 * Red Line Soft corp.
 */
public class ThreadPool {
    /**
     * Status work
     */
    volatile boolean workComplited = false;
    /**
     * Poll threads.
     */
    private ThreadCore[] poolThreads;

    /**
     * Poll works.
     */
    private Queue<Work> queueWorks = new LinkedList<>();

    /**
     * Construct.
     */
    public ThreadPool() {
        int amountOfCore = Runtime.getRuntime().availableProcessors();
        poolThreads = new ThreadCore[amountOfCore];
        for (int i = 0; i < amountOfCore; i++) {
            poolThreads[i] = new ThreadCore();
            poolThreads[i].start();
        }
    }

    /**
     * Add work.
     *
     * @param work
     */
    public void add(Work work) {
        synchronized (queueWorks) {
            queueWorks.add(work);
            queueWorks.notifyAll();
        }
    }

    /**
     * End of work.
     */
    public void endOfWork() {
        this.workComplited = true;
        for (Thread thread : poolThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Thread core.
     */
    private class ThreadCore extends Thread {
        @Override
        public void run() {
            while (true) {
                Work itemWork = null;
                synchronized (queueWorks) {
                    while (queueWorks.isEmpty()) {
                        if (workComplited) {
                            break;
                        }
                        try {
                            queueWorks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    itemWork = queueWorks.poll();
                }
                if (itemWork != null) {
                    itemWork.run();
                    System.out.println("Work " + itemWork.getNameOfWork() + " completed");
                } else {
                    System.out.println(Thread.currentThread().getName() + " end of work");
                    break;
                }

            }
        }
    }
}
