package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.AI.Contract.ITigerDenBrain;
import TigersDen.BL.AI.DataModel.Move;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.DI.InjectorStorage;

public class CpuPlayer extends AbstractPlayer{
    private ITigerDenBrain brain;
    private IBoard board;

    public CpuPlayer(String name, String color, String role) {
        super(name, color, role);
        this.brain = InjectorStorage.getInjector().getInstance(ITigerDenBrain.class);
        this.board = InjectorStorage.getInjector().getInstance(IBoard.class);
    }

    @Override
    public void play(ICell cellClicked) throws Exception {
        if (cellClicked != null)
        {
            throw new Exception("The play method of the CpuPlayer class should not be called with a cell.");
        }
        //Something is wrong here  - the is changed after the ai. piece onto the tigerden is moved!
        Move AIMove = brain.minimax (board, 0, true);
        MovingDetails movingDetails = generateMovingDetailsFromAIMove(AIMove);
        movementService.ApplyMove(movingDetails);
    }

    private MovingDetails generateMovingDetailsFromAIMove(Move aIMove) {
        ICoordinate sourceCoordinate = null;
        if (aIMove.getSourceColumn() == 4 && aIMove.getSourceRow() == -1) {
            sourceCoordinate = Coordinate.createSpacialInstance();
        } else {
            sourceCoordinate = Coordinate.createInstance(aIMove.getSourceRow(), aIMove.getSourceColumn(), false);
        }
        ICell sourceCell = board.getCell(sourceCoordinate);
        IPiece movingPiece = sourceCell.getPieceOnIt();
        if (movingPiece == null) {
            throw new NullPointerException("generateMovingDetailsFromAIMove: Moving piece is null.");
        }
        ICoordinate targetCoordinate = null;
        if (aIMove.getTargetColumn() == 4 && aIMove.getTargetRow() == -1) {
            targetCoordinate = Coordinate.createSpacialInstance();
        } else {
            targetCoordinate = Coordinate.createInstance(aIMove.getTargetRow(), aIMove.getTargetColumn(), false);
        }
        ICell targetCell = board.getCell(targetCoordinate);
        return new MovingDetails(movingPiece, sourceCell, targetCell);
    }

    @Override
    public IPlayer clone() {
        return new CpuPlayer(getName(), getColor(), getRole());
    }
}
