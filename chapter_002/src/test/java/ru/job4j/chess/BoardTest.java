package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.ImpossibleMoveExeption;
import ru.job4j.chess.exception.OccupiedWayException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Admin on 17.08.2017.
 */
public class BoardTest {
    /**
     * Test Occupied Way Exception.
     *
     * @throws OccupiedWayException OccupiedWayException
     * @throws FigureNotFoundException FigureNotFoundException
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    @Test(expected = OccupiedWayException.class)
    public void whenMoveInToBusyCellHaveOccupiedWayException() throws OccupiedWayException, FigureNotFoundException, ImpossibleMoveExeption {
        Board board = new Board();
        board.startGame();
        Cell[][] cells = board.getCells();
        board.move(cells[0][2], cells[1][3]);
    }

    /**
     * Test figure not found Exception.
     *
     * @throws OccupiedWayException OccupiedWayException
     * @throws FigureNotFoundException FigureNotFoundException
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenMoveFromCellWereNotFigureWeHaveFigureNotFoundException() throws FigureNotFoundException, OccupiedWayException, ImpossibleMoveExeption {
        Board board = new Board();
        board.startGame();
        Cell[][] cells = board.getCells();
        board.move(cells[2][2], cells[1][3]);
    }

    /**
     * Test Impossible Move Exeption.
     *
     * @throws OccupiedWayException OccupiedWayException
     * @throws FigureNotFoundException FigureNotFoundException
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    @Test(expected = ImpossibleMoveExeption.class)
    public void whenMoveImpossibleForThisFigureWeHaveImpossibleMoveException() throws ImpossibleMoveExeption, OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        board.startGame();
        Cell[][] cells = board.getCells();
        board.move(cells[0][2], cells[2][2]);
    }

    /**
     * Test possible move.
     * @throws OccupiedWayException OccupiedWayException
     * @throws FigureNotFoundException FigureNotFoundException
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    @Test
    public void whenMoviesPossibleWeHaveTrue() throws OccupiedWayException, ImpossibleMoveExeption, FigureNotFoundException {
        Board board = new Board();
        board.startGame();
        Cell[][] cells = board.getCells();
        boolean rezult = board.move(cells[0][2], cells[2][0]);
        assertThat(true, is(rezult));
    }


}