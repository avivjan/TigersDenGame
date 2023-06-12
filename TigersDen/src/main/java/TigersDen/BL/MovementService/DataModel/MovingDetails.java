package TigersDen.BL.MovementService.DataModel;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.DI.InjectorStorage;

public class MovingDetails {
    private int pieceMovementSpeed;
    private IPiece movingPiece;
    private ICell sourceCell;
    private ICell targetCell;
    private ICoordinate capturedPieceCor;
    private int evaluationAfterTheMove;
    

    public MovingDetails(IPiece piece,ICell sourceCell,ICell target,ICoordinate capturedPieceCor ,int evaluationAfterTheMove) {
        this.movingPiece = piece;
        this.targetCell = target;
        this.sourceCell = sourceCell;
        this.evaluationAfterTheMove = evaluationAfterTheMove;
        this.capturedPieceCor = capturedPieceCor;
        this.pieceMovementSpeed = InjectorStorage.getInjector().getInstance(IConfigurationService.class).GetPieceMovementSpeed();
    }

    public MovingDetails(IPiece piece,ICell sourceCell,ICell target) {
        this.movingPiece = piece;
        this.targetCell = target;
        this.sourceCell = sourceCell;
        this.pieceMovementSpeed = InjectorStorage.getInjector().getInstance(IConfigurationService.class).GetPieceMovementSpeed();
    }

    public int getEvaluationAfterTheMove() {
        return evaluationAfterTheMove;
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

    public void setEvaluationAfterTheMove(int evaluationAfterTheMove2) {
        this.evaluationAfterTheMove = evaluationAfterTheMove2;
    }

    public ICoordinate getCapturedPieceCoordinate() {
        return capturedPieceCor;
        
    }
}
