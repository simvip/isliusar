package testtask;

import static testtask.Where.LEFT;
import static testtask.Where.*;

/**
 * Created by Ivan Sliusar on 23.11.2017.
 * Red Line Soft corp.
 */
public class Bomberman extends Hero implements Runnable {
    /**
     * Construct.
     *
     * @param game GameBomberman
     */
    public Bomberman(GameBomberman game) {
        super("Dodick", game);
    }

    /**
     * Move hero left.
     *
     * @return boolean
     */
    public boolean moveLeft() {
        return move(LEFT);
    }

    /**
     * Move hero right.
     *
     * @return boolean
     */
    public boolean moveRight() {
        return move(RIGHT);
    }

    /**
     * Move hero Up.
     *
     * @return boolean
     */
    public boolean moveUP() {
        return move(UP);
    }

    /**
     * Move hero Down.
     *
     * @return boolean
     */
    public boolean moveDown() {
        return move(DOWN);
    }

    /**
     * Override run
     */
    @Override
    public void run() {
        try {
            placeOnBoard();
            boolean moveIsDone = false;
            while (game.stillGaming) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // check the ability to move

                for (Where where : Where.values()) {
                    moveIsDone = move(where);
                    if (moveIsDone) {
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    moveIsDone = move(where);
                    if (moveIsDone) {
                        break;
                    }
                }
                if (!moveIsDone) {
                    game.stillGaming = false;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR OF HERO");
        } finally {
            if (locker.isLocked()) {
                locker.unlock();
            }
        }

    }
}