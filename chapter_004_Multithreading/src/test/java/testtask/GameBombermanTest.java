package testtask;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 23.11.2017.
 * Red Line Soft corp.
 */
public class GameBombermanTest {
    /**
     * Test game.
     */
    @Test
    public void start() {
        GameBomberman gameBomberman = new GameBomberman();
        gameBomberman.start();
    }

}