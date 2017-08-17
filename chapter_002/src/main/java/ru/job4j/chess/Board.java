package ru.job4j.chess;

import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.ImpossibleMoveExeption;
import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.figure.Bishop;
import ru.job4j.chess.figure.Figure;
import ru.job4j.chess.figure.Pawn;
import ru.job4j.chess.movesrategy.MoveLikeBishop;
import ru.job4j.chess.movesrategy.MoveLikePawn;

/**
 * Created by Admin on 15.08.2017.
 */
public class Board {
    /**
     * massive fo figures.
     */
    private Figure[] figures;
    /**
     * massive of cells.
     */
    private Cell[][] cells;


    /**
     * Move Figure.
     *
     * @param source Cell move from cell
     * @param dist   Cell move to cell
     * @return true if everything is good.
     * @throws ImpossibleMoveExeption trows ImpossibleMoveExeption
     * @throws OccupiedWayException trows OccupiedWayException
     * @throws FigureNotFoundException trows FigureNotFoundException
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveExeption, OccupiedWayException, FigureNotFoundException {

        if (source.getFigureInThisCell() == null) {
            throw new FigureNotFoundException();
        }

        Figure currentFigure = source.getFigureInThisCell();
        Cell[] ways = currentFigure.way(cells, dist);
        for (Cell cell : ways) {
            if (cell.getFigureInThisCell() != null) {
                throw new OccupiedWayException();
            }
        }

        Figure newFigure = currentFigure.clone(dist);
        dist.setFigureInThisCell(newFigure);

        source.setFigureInThisCell(null);

        return true;
    }

    /**
     * Construct.
     */
    public Board() {
        figures = new Figure[2];
        cells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[j][i] = new Cell(j, i);
            }
        }
    }

    /**
     * Start game.
     */
    public void startGame() {
        new Board();

        Bishop bishop = new Bishop(cells[0][2]);
        bishop.setStrategyMove(new MoveLikeBishop());
        cells[0][2].setFigureInThisCell(bishop);
        figures[0] = bishop;

        Pawn pawn = new Pawn(cells[1][3]);
        pawn.setStrategyMove(new MoveLikePawn());
        cells[1][3].setFigureInThisCell(pawn);
        figures[1] = pawn;

    }

    /**
     * Getter.
     * @return Figure[]
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * Getter.
     * @return Cell[][]
     */
    public Cell[][] getCells() {
        return cells;
    }
}
