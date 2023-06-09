package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.BussinessLogic.MovementService;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.DI.InjectorStorage;

public class HumanPlayer extends AbstractPlayer{
    IBoard board;
    IMovementService movementService;
    public HumanPlayer(String name, String color, String role) {
        super(name, color, role);
        board = InjectorStorage.getInjector().getInstance(IBoard.class);
        movementService = InjectorStorage.getInjector().getInstance(MovementService.class);
    }

    @Override
    public boolean play(ICell cellClicked) {
        try {
            CellStatus cellStatus = cellClicked.getStatus();
            
            if (cellStatus == CellStatus.Selected) {
                board.DeselectCellIfExists();
                return false;
            }
            if (cellStatus == CellStatus.Option || cellStatus == CellStatus.OptionWithCapture) {
                if (cellStatus == CellStatus.OptionWithCapture)
                {
                    //capture
                    cellClicked.getPieceOnIt().capture();
                    cellClicked.setPieceOnIt(null);
                }
                //move
                ICell moveFrom =  board.getSelectedCell();
                ICell moveTo = cellClicked;
                IPiece pieceToMove = moveFrom.getPieceOnIt();

                movementService.ApplyMove(pieceToMove,moveFrom, moveTo);
                return true;
            }
            if (!cellClicked.canBeSelected()) {
                System.out.println("Cell cannot be selected");
                return false;
            }
            // Here we know that the cell is not selected, can be selected and is not an
            // option.
            board.selectCell(cellClicked);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
