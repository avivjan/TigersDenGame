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

public class TigerPiece extends AbstractPiece {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public TigerPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player, InjectorStorage.getInjector().getInstance(IMovementService.class));
    }

    @Override
    public List<ICell> getOptionalMovements(IBoard board) {

        try {
            List<ICell> optionalMovements = new ArrayList<>();

            ICoordinate currentCoordinate = coordinate;
            int currentRow = currentCoordinate.getRow();
            int currentColumn = currentCoordinate.getColumn();

            for (int[] direction : DIRECTIONS) {
                int newRow = currentRow + direction[0];
                int newColumn = currentColumn + direction[1];

                if (isValidCoordinate(board, newRow, newColumn)) {
                    ICoordinate newCoordinate = Coordinate.createInstance(newRow, newColumn, false);
                    ICell newCell = board.getCell(newCoordinate);

                    if (newCell.isEmpty()) {
                        optionalMovements.add(newCell);
                    } else if (newCell.getPieceOnIt() instanceof PawnPiece) {
                        int nextRow = newRow + direction[0];
                        int nextColumn = newColumn + direction[1];

                        if (isValidCoordinate(board, nextRow, nextColumn)) {
                            ICoordinate nextCoordinate = Coordinate.createInstance(nextRow, nextColumn, false);
                            ICell nextCell = board.getCell(nextCoordinate);

                            if (nextCell.isEmpty()) {
                                optionalMovements.add(nextCell);
                            }
                        }
                    }
                }
            }
            return optionalMovements;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private boolean isValidCoordinate(IBoard board, int row, int column) {
        return row >= 0 && row < board.getNumOfRows() && column >= 0 && column < board.getNumOfCols();
    }
}
