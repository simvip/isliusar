package nonblockingalgoritm;

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
                for (int i = 0; i < 10; i++) {

                    Model model = cache.getById(2);
                    model.setName(Thread.currentThread().getName() + " " + i);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cache.update(model);
                }
            }
        }, "Thread 1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 100; i < 105; i++) {

                    Model model = cache.getById(2);
                    System.out.println("Получил объект для измениня, версия " + model.getVersion());
                    model.setName(Thread.currentThread().getName() + " " + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пока спал верися поменялася " + model.getVersion());
                    cache.update(model);

                }
            }
        }, "Thread 2");

        thread1.start();
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