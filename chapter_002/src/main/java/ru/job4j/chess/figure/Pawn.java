package ru.job4j.chess.figure;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;
import ru.job4j.chess.movesrategy.StrategyMove;

/**
 * Created by Admin on 16.08.2017.
 */
public class Pawn extends Figure {

    /**
     * Стратегия хода фигуры слона.
     */
    private StrategyMove strategyMove;

    /**
     * Конструктор.
     *
     * @param position Cell начальная позиция фигуры.
     */
    public Pawn(Cell position) {
        super(position);
    }

    /**
     * Выполнить ход.
     *
     * @param dist
     * @return Cell[] массив ячеек через которые будет проходить фигура.
     * @throws ImpossibleMoveExeption
     */
    @Override
    public Cell[] way(Cell[][] cells, Cell dist) throws ImpossibleMoveExeption {
        return strategyMove.getMove(cells, this.getPosition(), dist);
    }

    /**
     * Задать стратегию хода.
     *
     * @param strategyMove StrategyMove
     */
    public void setStrategyMove(StrategyMove strategyMove) {
        this.strategyMove = strategyMove;
    }

    /**
     * Создать новую фигура в заданоой клетке.
     *
     * @param position
     * @return Figure
     */
    @Override
    public Figure clone(Cell position) {
        Pawn pawn = new Pawn(position);
        pawn.setStrategyMove(this.strategyMove);
        return pawn;
    }
}
