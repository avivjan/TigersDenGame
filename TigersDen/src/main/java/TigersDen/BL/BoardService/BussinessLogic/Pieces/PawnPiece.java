package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.DI.InjectorStorage;

public class PawnPiece extends AbstractPiece {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public PawnPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player, InjectorStorage.getInjector().getInstance(IMovementService.class));
    }

    @Override
    public List<ICell> getOptionalMovements(IBoard board) throws Exception {

        try {
            List<ICell> optionalMovements = new ArrayList<>();

            ICoordinate currentCoordinate = getCoordinate();
            int currentRow = currentCoordinate.getRow();
            int currentColumn = currentCoordinate.getColumn();

            for (int[] direction : DIRECTIONS) {
                int newRow = currentRow + direction[0];
                int newColumn = currentColumn + direction[1];

                if (isValidCoordinate(board, newRow, newColumn)) {
                    ICoordinate newCoordinate = Coordinate.createInstance(newRow, newColumn, false);
                    ICell newCell;
                    newCell = board.getCell(newCoordinate);
                    if (newCell.isEmpty()) {
                        optionalMovements.add(newCell);
                    }
                }
            }
            return optionalMovements;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public PawnPiece clone() {
        PawnPiece cloned =  new PawnPiece(getCoordinate().clone(), getOwningPlayer().clone());
        cloned.id = id;
        cloned.isCaptured = isCaptured;
        return cloned;
    }

    private boolean isValidCoordinate(IBoard board, int row, int column) {
        return row >= 0 && row < board.getNumOfRows() && column >= 0 && column < board.getNumOfCols();
    }
}
