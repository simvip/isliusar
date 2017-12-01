package testtask;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ivan Sliusar on 22.11.2017.
 * Red Line Soft corp.
 */
abstract class Hero {
    /**
     * Name.
     */
    private String name;

    /**
     * Coordinate x.
     */
    private int x = 0;

    /**
     * Coordinate y.
     */
    private int y = 0;

    /**
     * Locker.
     */
    public ReentrantLock locker;

    /**
     * Current game.
     */
    public GameBomberman game;

    /**
     * Construct.
     *
     * @param name String
     * @param game GameBomberman
     */
    public Hero(String name, GameBomberman game) {
        this.game = game;
        this.name = name;
    }

    /**
     * Place current hero on board.
     */
    public void placeOnBoard() {
        boolean placeIsDone = false;
        while (!placeIsDone) {
            this.x = (int) (Math.random() * game.board.length);
            this.y = (int) (Math.random() * game.board[0].length);
            if (!game.board[x][y].isLocked()) {
                this.locker = game.board[x][y];
                this.locker.lock();
                break;
            }
        }
    }

    /**
     * Move hero.
     *
     * @param whereMove Where
     * @return boolean
     */
    public boolean move(Where whereMove) {
        int newX = 0;
        int newY = 0;
        switch (whereMove) {
            case LEFT:
                newX = this.x - 1;
                break;
            case RIGHT:
                newX = this.x + 1;
                break;
            case UP:
                newY = this.y - 1;
                break;
            case DOWN:
                newY = this.y + 1;
                break;
            default:
                break;
        }

        // Go outside the playing field
        if (newX < 0 || newY < 0) {
            return false;
        }

        if (newX > game.board.length || newY > game.board[0].length) {
            return false;
        }
        // cell is not empty
        if (game.board[newX][newY].isLocked()) {
            return false;
        }
        this.locker.unlock();
        this.locker = game.board[newX][newY];
        this.locker.lock();

        this.x = newX;
        this.y = newY;

        System.out.printf("%s go to [%s:%s] %n", this.name, x, y, System.lineSeparator());

        return true;
    }

}
