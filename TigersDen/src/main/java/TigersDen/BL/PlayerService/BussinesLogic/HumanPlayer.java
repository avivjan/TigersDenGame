package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DI.InjectorStorage;

public class HumanPlayer extends AbstractPlayer{
    IBoard board;
    public HumanPlayer(String name, String color) {
        super(name, color);
        board = InjectorStorage.getInjector().getInstance(IBoard.class);
    }

    @Override
    public void play(ICell cellClicked) {
        try {
            CellStatus cellStatus = cellClicked.getStatus();
            

            if (cellStatus == CellStatus.Selected) {
                board.DeselectCellIfExists();
                return;
            }
            if (cellStatus == CellStatus.Option || cellStatus == CellStatus.OptionWithCapture) {
                if (cellStatus == CellStatus.OptionWithCapture)
                {
                    //capture
                    cellClicked.getPieceOnIt().capture();
                    cellClicked.setPieceOnIt(null);
                }
                //move
                // ICell moveFrom =  board.getSelectedCell();
                // ICell moveTo = cellClicked;
                // IPiece pieceToMove = moveFrom.getPieceOnIt();

                // pieceToMove.startMoving(moveTo);
                // board.DeselectCellIfExists();
                // moveFrom.setPieceOnIt(null);
                // moveTo.setPieceOnIt(pieceToMove);
            }
            if (!cellClicked.canBeSelected()) {
                System.out.println("Cell cannot be selected");
                return;
            }
            // Here we know that the cell is not selected, can be selected and is not an
            // option.
            board.selectCell(cellClicked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
