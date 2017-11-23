package testtask;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ivan Sliusar on 23.11.2017.
 * Red Line Soft corp.
 */
public class Monster implements Runnable {
    /**
     * List of monsters.
     */
    private List<BaseMonster> monsters;
    /**
     * Current game.
     */
    private GameBomberman game;

    /**
     * Construct.
     *
     * @param game            GameBomberman
     * @param amoutnOfMonster int
     */
    public Monster(GameBomberman game, int amoutnOfMonster) {
        this.game = game;
        monsters = new ArrayList<>(amoutnOfMonster);
        for (int i = 0; i < amoutnOfMonster; i++) {
            monsters.add(new BaseMonster("Grizzly #" + i, game));
        }
    }

    /**
     * Base monster.
     */
    private class BaseMonster extends Hero {
        public BaseMonster(String name, GameBomberman game) {
            super(name, game);
        }
    }

    /**
     * Override run.
     */
    @Override
    public void run() {
        try {
            int pick = 0;
            int maxDirections = 4;

            for (BaseMonster monster : monsters) {
                monster.locker.lock();
            }

            while (game.stillGaming) {

                for (BaseMonster monster : monsters) {
                    pick = (int) Math.random() * maxDirections;
                    // check the ability to move
                    if (!monster.move(Where.values()[pick])) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR OF MONSTER");
        } finally {
            for (BaseMonster monster : monsters) {
                if (monster.locker.isLocked()) {
                    monster.locker.unlock();
                }
            }

        }
    }
}
