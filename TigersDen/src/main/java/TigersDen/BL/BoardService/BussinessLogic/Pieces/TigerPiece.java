package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class TigerPiece extends AbstractPiece {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public TigerPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player);
    }

    @Override
    public List<MovingDetails> getOptionalMovements(IBoard board) throws Exception {

        try {
            List<MovingDetails> optionalMovements = new ArrayList<>();

            ICoordinate currentCoordinate = coordinate;
            int currentRow = currentCoordinate.getRow();
            int currentColumn = currentCoordinate.getColumn();
            ICell currentCell = board.getCell(currentCoordinate);

            for (int[] direction : DIRECTIONS) {
                int newRow = currentRow + direction[0];
                int newColumn = currentColumn + direction[1];

                if (isValidCoordinate(board, newRow, newColumn)) {
                    ICoordinate newCoordinate = Coordinate.createInstance(newRow, newColumn, false);
                    ICell newCell = board.getCell(newCoordinate);

                    if (newCell.isEmpty()) {
                        optionalMovements.add(new MovingDetails(this, currentCell, newCell, null, 0));
                    } else if (newCell.getPieceOnIt() instanceof PawnPiece) {
                        int nextRow = newRow + direction[0];
                        int nextColumn = newColumn + direction[1];

                        if (isValidCoordinate(board, nextRow, nextColumn)) {
                            ICoordinate nextCoordinate = Coordinate.createInstance(nextRow, nextColumn, false);
                            ICell nextCell = board.getCell(nextCoordinate);

                            if (nextCell.isEmpty()) {
                                optionalMovements.add(new MovingDetails(this, currentCell, nextCell, newCell.getCoordinate(), 0));
                            }
                        }
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
    public IPiece clone() {
        TigerPiece cloned = new TigerPiece(getCoordinate().clone(), getOwningPlayer().clone());
        cloned.isCaptured = isCaptured;
        return cloned;
    }

    private boolean isValidCoordinate(IBoard board, int row, int column) {
        return row >= 0 && row < board.getNumOfRows() && column >= 0 && column < board.getNumOfCols();
    }
}
