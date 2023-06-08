package TigersDen.BL.MovementService.DataModel;

import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;

public class MovingDetails {
    private double pieceMovementSpeed = 10;//config
    private IPiece movingPiece;
    private ICell targetCell;

    public MovingDetails(IPiece piece, ICell target) {
        this.movingPiece = piece;
        this.targetCell = target;
    }

    public double getPieceMovementSpeed() {
        return pieceMovementSpeed;
    }

    public void setPieceMovementSpeed(double pieceMovementSpeed) {
        this.pieceMovementSpeed = pieceMovementSpeed;
    }

    public IPiece getMovingPiece() {
        if (movingPiece == null) {
            throw new NullPointerException("Moving piece is null.");
        }
        return movingPiece;
    }

    public void setMovingPiece(IPiece movingPiece) {
        this.movingPiece = movingPiece;
    }

    public ICell getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(ICell targetCell) {
        this.targetCell = targetCell;
    }   
}
