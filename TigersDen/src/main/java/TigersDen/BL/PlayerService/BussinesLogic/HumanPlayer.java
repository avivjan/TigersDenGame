package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.DI.InjectorStorage;

public class HumanPlayer extends AbstractPlayer{
    IBoard board;
    

    public HumanPlayer(String name, String color, String role) {
        super(name, color, role);
        board = InjectorStorage.getInjector().getInstance(IBoard.class);
        isHuman = true;
       
    }

    @Override
    public void play(ICell cellClicked) throws Exception {
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
                    cellClicked.getPieceOnIt().capture(null);
                    cellClicked.setPieceOnIt(null);
                }
                movementService.ApplyMove(board.getSelectedCell().getPieceOnIt(),board.getSelectedCell(), cellClicked);
            }
            if (!cellClicked.canBeSelected()) {
                System.out.println("Cell cannot be selected");
                return;
            }
            // Here we know that the cell is not selected, can be selected and is not an option.
            board.selectCell(cellClicked);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public IPlayer clone() {
        return new HumanPlayer(getName(), getColor(), getRole());
    }
}
