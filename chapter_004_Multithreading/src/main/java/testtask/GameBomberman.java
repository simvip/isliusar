package testtask;

import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Ivan Sliusar on 22.11.2017.
 * Red Line Soft corp.
 */
public class GameBomberman {
    /**
     * Board for game.
     */
    public final ReentrantLock[][] board;

    /**
     * Status game.
      */
    public volatile boolean stillGaming = true;

    /**
     * Construct.
     */
    public GameBomberman() {
        this.board = new ReentrantLock[50][10];
    }

    /**
     * Start game.
     */
    public void start() {
        initialLockers();

        Thread heroThread = new Thread(new Bomberman(this));
        Thread monstersThread = new Thread(new Monster(this, 5));

        heroThread.start();
        monstersThread.start();

        System.out.println("Start game");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stillGaming = false;
        try {
            heroThread.join();
            monstersThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End game");

    }

    /**
     * Initial lockers on board.
     */
    private void initialLockers() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }
}
