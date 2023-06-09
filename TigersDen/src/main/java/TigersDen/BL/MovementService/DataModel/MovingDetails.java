package TigersDen.BL.MovementService.DataModel;

import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.DI.InjectorStorage;

public class MovingDetails {
    private int pieceMovementSpeed;
    private IPiece movingPiece;
    private ICell sourceCell;
    private ICell targetCell;
    

    public MovingDetails(IPiece piece,ICell sourceCell,ICell target) {
        this.movingPiece = piece;
        this.targetCell = target;
        this.sourceCell = sourceCell;
        this.pieceMovementSpeed = InjectorStorage.getInjector().getInstance(IConfigurationService.class).GetPieceMovementSpeed();
    }

    public int getPieceMovementSpeed() {
        return pieceMovementSpeed;
    }

    public void setPieceMovementSpeed(int pieceMovementSpeed) {
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
        if (targetCell == null) {
            throw new NullPointerException("Target cell is null.");
        }
        if (targetCell.getCoordinate() == null) {
            throw new NullPointerException("Target cell coordinate is null.");
        }
        if ((targetCell.getCoordinate().isOnBoard()) == false) {
            throw new IllegalArgumentException("Target cell coordinate is not on board.");
        }
        this.targetCell = targetCell;
    }  
    
    public ICell getSourceCell() {
        return sourceCell;
    }
}
