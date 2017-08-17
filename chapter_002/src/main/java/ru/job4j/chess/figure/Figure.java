package ru.job4j.chess.figure;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;

/**
 * Created by Admin on 15.08.2017.
 */
public abstract class Figure {
    /**
     * Position of figure.
     */
    private final Cell position;

    /**
     * Construct.
     *
     * @param position Cell in witch there is a figure
     */
    protected Figure(Cell position) {
        this.position = position;
    }

    /**
     * Way of figire go.
     *
     * @param dist Cell of destination
     * @param cells Cell[][]
     * @return Cell[] massive for cells on witch figure move
     * @throws ImpossibleMoveExeption ImpossibleMoveExeption
     */
    public abstract Cell[] way(Cell[][] cells, Cell dist) throws ImpossibleMoveExeption;

    /**
     * Clone current figure on another cell.
     *
     * @param position Cell new position of figure
     * @return Figure
     */
    public abstract Figure clone(Cell position);

    /**
     * Getter.
     * @return Cell
     */
    public Cell getPosition() {
        return position;
    }
}
