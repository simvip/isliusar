package nonblockingalgoritm1;

import org.junit.Test;

/**
 * Created by Ivan Sliusar on 20.11.2017.
 * Red Line Soft corp.
 */
public class CacheForModelTest {
    /**
     * Test.
     */
    @Test
    public void testCache() {
        CacheForModel cache = new CacheForModel();

        Model modelFirst = new Model(1, "First");
        cache.add(modelFirst);
        Model modelSecond = new Model(2, "Second");
        cache.add(modelSecond);
        Model modelThird = new Model(3, "Third");
        cache.add(modelThird);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    Model updateModel = new Model(1, "First");
                    updateModel.setName(Thread.currentThread().getName() + " " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cache.update(updateModel);
                }
            }
        }, "Thread 1");

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 100; i < 150; i++) {
                    Model updateModel = new Model(1, "First");
                    updateModel.setName(Thread.currentThread().getName() + " " + i);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cache.update(updateModel);

                }
            }
        }, "Thread 2");

        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cache.delete(modelThird);

        System.out.println(cache.toString());
    }

}