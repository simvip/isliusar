package ru.job4j.chess.movesrategy;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;

/**
 * Created by Admin on 16.08.2017.
 */
public class MoveLikePawn implements StrategyMove {
    /**
     * Move strategy of Pawn.
     *
     * @param from Cell form witch figure move
     * @param to   Cell to cell figure move
     * @return massive Cell[] cells on witch the figure move
     */
    @Override
    public Cell[] getMove(Cell[][] cells, Cell from, Cell to) throws ImpossibleMoveExeption {
        return new Cell[0];
    }
}
