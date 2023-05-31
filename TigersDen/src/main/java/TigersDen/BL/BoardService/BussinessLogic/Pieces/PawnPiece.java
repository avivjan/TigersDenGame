package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class PawnPiece extends AbstractPiece {

    public PawnPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player);   
    }

    @Override
    public List<ICell> getOptionalMovements(IBoard board) throws Exception {
        List<ICell> optionalMovements = new ArrayList<>();
        int currentX = getCoordinate().getRow();
        int currentY = getCoordinate().getColumn();

        // Define the eight possible directions for king movement
        int[] xDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] yDirections = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Check all eight directions for valid movements
        for (int i = 0; i < xDirections.length; i++) {
            int x = currentX + xDirections[i];
            int y = currentY + yDirections[i];

            if (Coordinate.createInstance(x, y, false).isValidCoordinate()) {
                ICell cell = board.getCell(Coordinate.createInstance(x, y, false));
                if (cell.getPieceOnIt() == null || cell.getPieceOnIt().getOwningPlayer() != getOwningPlayer()) {
                    optionalMovements.add(cell);
                }
            }
        }

        // Print the optional movements
        System.out.println("Optional Movements:");
        for (ICell cell : optionalMovements) {
            System.out.println(cell.getCoordinate());
            board.addOptionCells(cell);
        }

        return optionalMovements;
    }
}
