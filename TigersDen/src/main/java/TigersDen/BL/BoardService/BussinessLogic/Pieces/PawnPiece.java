package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class PawnPiece extends AbstractPiece {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public PawnPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player);
    }

    @Override
    public List<MovingDetails> getOptionalMovements(IBoard board) throws Exception {

        try {
            List<MovingDetails> optionalMovements = new ArrayList<>();

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
                        ICell currentCell = board.getCell(coordinate);
                        ICell destinationCell = board.getCell(newCoordinate);

                        MovingDetails movingDetails = new MovingDetails(this, currentCell, destinationCell, null, 0);
                        optionalMovements.add(movingDetails);
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
        cloned.isCaptured = isCaptured;
        return cloned;
    }

    private boolean isValidCoordinate(IBoard board, int row, int column) {
        return row >= 0 && row < board.getNumOfRows() && column >= 0 && column < board.getNumOfCols();
    }
}
