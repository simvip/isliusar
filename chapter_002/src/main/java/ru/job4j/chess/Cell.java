package ru.job4j.chess;

import ru.job4j.chess.figure.Figure;

/**
 * Created by Admin on 15.08.2017.
 */
public class Cell {
    /**
     * x coordinate.
     */
    private int x;
    /**
     * y coorditane.
     */
    private int y;
    /**
     * figure in this cell.
     */
    public Figure figureInThisCell = null;

    /**
     * Construct.
     * @param x int
     * @param y int
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Setter.
     *
     * @param figureInThisCell Figure
     */
    public void setFigureInThisCell(Figure figureInThisCell) {
        this.figureInThisCell = figureInThisCell;
    }

    /**
     * Getter.
     *
     * @return Figure
     */
    public Figure getFigureInThisCell() {
        return figureInThisCell;
    }

    /**
     * Getter x.
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Getter y.
     * @return int
     */
    public int getY() {
        return y;
    }
}
