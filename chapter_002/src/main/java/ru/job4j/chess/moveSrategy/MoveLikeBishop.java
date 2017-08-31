package ru.job4j.chess.movesrategy;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16.08.2017.
 */
public class MoveLikeBishop implements StrategyMove {
    /**
     * Move strategy of bishop.
     *
     * @param from Cell form witch figure move
     * @param to   Cell to cell figure move
     * @return massive Cell[] cells on witch the figure move
     */
    @Override
    public Cell[] getMove(Cell[][] cells, Cell from, Cell to) throws ImpossibleMoveExeption {
        int x = from.getX();
        int y = from.getY();
        int deltaX;
        int deltaY;

        if (to.getX() > from.getX()) {
            deltaX = 1;
            if (to.getY() > from.getY()) {
                deltaY = 1;
            } else {
                deltaY = -1;
            }
        } else {
            deltaY = -1;
            if (to.getX() > from.getX()) {
                deltaX = 1;
            } else {
                deltaX = -1;
            }
        }
        List<Cell> array = new ArrayList<>();
        boolean stepIsPossible = false;
        while (x >= 0 && y >= 0 && x <= 7 && y <= 7) {

            Cell currentCell = cells[x][y];

            if (currentCell == from) {
                x += deltaX;
                y += deltaY;
                continue;
            }
            array.add(currentCell);

            if (currentCell == to) {
                stepIsPossible = true;
                break;
            }
            x += deltaX;
            y += deltaY;
        }
        if (stepIsPossible) {
            return array.toArray(new Cell[array.size()]);
        } else {
            throw new ImpossibleMoveExeption();
        }
    }
}
