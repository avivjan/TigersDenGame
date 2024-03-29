package TigersDen.BL.MovementService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class MovementService implements IMovementService {
    private MovingDetails movingDetails;
    private IBoard board;
    private ITurnManager turnManager;

    @Inject
    public MovementService(IBoard board, ITurnManager turnManager) {
        this.board = board;
        this.turnManager = turnManager;
    }

    @Override
    public void move() throws Exception {
        if (movingDetails == null) {
            return;
        }
        
        IPiece movingPiece = movingDetails.getMovingPiece();
        ICell targetCell = movingDetails.getTargetCell();

        if (movingPiece == null) {
            throw new NullPointerException("Moving piece is null.");
        }
        if (targetCell == null) {
            throw new NullPointerException("Target cell is null.");
        }

        // move
        double speed = movingDetails.getPieceMovementSpeed();
        double distance = movingPiece.getCoordinate().getDistanceTo(targetCell.getCoordinate());

        int currentX = movingPiece.getCoordinate().getXInPixels();
        int currentY = movingPiece.getCoordinate().getYInPixels();

        int targetX = targetCell.getCoordinate().getXInPixels();
        int targetY = targetCell.getCoordinate().getYInPixels();

        int deltaX = targetX - currentX;
        int deltaY = targetY - currentY;

        int newX = currentX;
        int newY = currentY;

        if (distance > speed) {
            //we calculate the rate of the movement in order to have exactly *speed* pixels per frame.
            int signX = deltaX > 0 ? 1 : -1;
            int signY = deltaY > 0 ? 1 : -1;
            Double ratex = Math.pow(deltaX / distance, 2);
            Double ratey = Math.pow(deltaY / distance, 2);
            ratex = ratex * signX;
            ratey = ratey * signY;
            Double vx = ratex * speed;
            Double vy = ratey * speed;

            newX += vx;
            newY += vy;
        } else {
            newX = targetX;
            newY = targetY;
        }

        if (Coordinate.isOnBoard(newX, newY, true) == false) {
            throw new Exception(" piece is moving out of the board.");
        }

        movingPiece.setCoordinate(Coordinate.createInstance(newX, newY, true));

        if (hasReached()) {
            System.out.println(movingPiece.getOwningPlayer().getName()+ 
                               " piece has just reached his destination");
            movingPiece.setCoordinate(targetCell.getCoordinate());
            board.DeselectCellIfExists();
            movingDetails = null;
            turnManager.setNextPlayerInTurn();
        }
    }

    private boolean hasReached() {
        IPiece movingPiece = movingDetails.getMovingPiece();
        ICell targetCell = movingDetails.getTargetCell();

        return movingPiece.getCoordinate().getDistanceTo(targetCell.getCoordinate()) < 0.1;
    }

    

    @Override
    public boolean isMoving() {
        return movingDetails != null;
    }

    @Override
    public boolean isMoving(IPiece piece) {
        return movingDetails != null && movingDetails.getMovingPiece() == piece;
    }

    @Override
    public void ApplyMove(IPiece pieceToMove,ICell moveFrom , ICell moveTo) throws Exception {
        AddMovingDetails(new MovingDetails(pieceToMove, moveFrom, moveTo));
        
        if (pieceToMove.getCoordinate().equals(moveTo.getCoordinate())) {
            throw new Exception("The piece is already in the target cell.");
        }
        board.DeselectCellIfExists();
        moveFrom.setPieceOnIt(null);
        moveTo.setPieceOnIt(pieceToMove);
    }
    @Override
    public void ApplyMove(MovingDetails movingDetails) throws Exception {
        ApplyMove(movingDetails.getMovingPiece(), movingDetails.getSourceCell(), movingDetails.getTargetCell());
    }

    private void AddMovingDetails(MovingDetails movingDetails2) throws Exception {
        if (isMoving()) {
            throw new Exception("Currently this is not possible to move two pieces at the same time");
        }
        this.movingDetails = movingDetails2;
    }
}
