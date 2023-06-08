package TigersDen.BL.MovementService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.MovementService.DataModel.MovingDetails;

public class MovementService implements IMovementService{
    private MovingDetails movingDetails;
    private IBoard board;

    @Inject
    public MovementService(IBoard board) {
        this.board = board;
    }

    @Override
    public void move() throws Exception {
        System.out.println("move");
        if (movingDetails == null ) {
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

        // Update the piece's position based on the movement speed and time
        double speed = movingDetails.getPieceMovementSpeed();
        double distance = movingPiece.getCoordinate().getDistanceTo(targetCell.getCoordinate());

        //move
        int currentX = movingPiece.getCoordinate().getXInPixels();
        int currentY = movingPiece.getCoordinate().getYInPixels();

        int targetX = targetCell.getCoordinate().getXInPixels();
        int targetY = targetCell.getCoordinate().getYInPixels();

        int deltaX = targetX - currentX;
        int deltaY = targetY - currentY;

        int newX = currentX;
        int newY = currentY;

        if (distance > speed) {
            Double vx = deltaX / distance * speed;
            Double vy = deltaY / distance * speed;
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

        if (hasReached())
        {
            System.out.println("MovementService: Piece" + movingPiece.getClass() 
                                + "has just reached his destination");
            movingPiece.setCoordinate(targetCell.getCoordinate());
            board.DeselectCellIfExists();
            movingDetails = null;
        }
    }

    private boolean hasReached() {
        IPiece movingPiece = movingDetails.getMovingPiece();
        ICell targetCell = movingDetails.getTargetCell();

        return movingPiece.getCoordinate().getDistanceTo(targetCell.getCoordinate()) < 0.01;
    }

    @Override
    public void AddMovingDetails(MovingDetails movingDetails2) throws Exception {
        if (isMoving())
        {
            throw new Exception("Currently this is not possible to move two pieces at the same time");
        }
        this.movingDetails = movingDetails2;
    }

    @Override
    public boolean isMoving() {
        return movingDetails != null;
    }

    @Override
    public boolean isMoving(IPiece piece) {
        return movingDetails != null && movingDetails.getMovingPiece() == piece;
    }

}
