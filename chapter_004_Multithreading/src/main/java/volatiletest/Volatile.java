package volatiletest;

/**
 * Created by Ivan Sliusar on 30.11.2017.
 * Red Line Soft corp.
 */
public class Volatile {
    static int j;
    volatile static int i;

    public static void main(String[] args) {
        i = 0;
        Thread t = new WhriteThread();
        t.start();
        new ReadThread().start();

    }

    static class WhriteThread extends Thread {
        @Override
        public void run() {
            while (i < 5) {
                System.out.println("increase i " + (++i));
                //System.out.println("increase j "+ (++j));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            int localValuei = i;
            int localValuej = j;
            while (localValuei < 5) {
                if (localValuei != i) {
                    localValuei = i;
                    System.out.println(localValuei);
                }
            }
        }
    }
}
