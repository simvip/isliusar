package ru.job4j.chess.movesrategy;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;

/**
 * Created by Admin on 24.07.2017.
 */
public interface StrategyMove {
    /**
     * Walk figure.
     *
     * @param from Cell
     * @param to   Cell
     * @param cells Cell[][]
     * @return Cell[]
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    Cell[] getMove(Cell[][] cells, Cell from, Cell to) throws ImpossibleMoveExeption;
}
