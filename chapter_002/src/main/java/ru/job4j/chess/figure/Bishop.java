package ru.job4j.chess.figure;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exception.ImpossibleMoveExeption;
import ru.job4j.chess.movesrategy.StrategyMove;

/**
 * Created by Admin on 15.08.2017.
 */
public class Bishop extends Figure {
    /**
     * Стратегия хода фигуры слона.
     */
    private StrategyMove strategyMove;

    /**
     * Конструктор.
     *
     * @param position текущая поизиция фигуры, при стартовом размещении координаты (0,2) или (0,5)
     */
    public Bishop(Cell position) {
        super(position);
    }


    /**
     * Клонировать фиругу в заданую ячейкуж.
     *
     * @param position
     * @return
     */
    @Override

    public Figure clone(Cell position) {
        Bishop bishop = new Bishop(position);
        bishop.setStrategyMove(this.strategyMove);
        return bishop;
    }

    /**
     * Ход фигуры.
     *
     * @param dist куда должна походить фигура.
     * @return Cell[] массив ячеек через которые будет ходить фигура.
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
}
