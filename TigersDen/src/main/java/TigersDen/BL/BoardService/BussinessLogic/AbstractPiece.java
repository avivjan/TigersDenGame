package TigersDen.BL.BoardService.BussinessLogic;

import java.util.List;
import java.util.UUID;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.DI.InjectorStorage;

public abstract class AbstractPiece implements IPiece {
    private IPlayer owningPlayer;
    protected boolean isCaptured;
    protected UUID id;
    protected ICoordinate coordinate;
    protected IMovementService movementService;
    protected IBoard thisBoard;

    public AbstractPiece(ICoordinate coordinate2, IPlayer owningPlayer) {
        this.movementService = InjectorStorage.getInjector().getInstance(IMovementService.class);
        this.coordinate = coordinate2;
        this.owningPlayer = owningPlayer;
        isCaptured = false;
        id = UUID.randomUUID();
        thisBoard = InjectorStorage.getInjector().getInstance(IBoard.class);

    }

    @Override
    public abstract List<MovingDetails> getOptionalMovements(IBoard board) throws Exception;
    @Override
    public abstract IPiece clone();

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    // We need to pass board because in the AI if we capture we need to updatge the cloned board and not the original one.
    public void capture(IBoard boardToMakeCaptureOnit) {
        if (boardToMakeCaptureOnit == null) {
            boardToMakeCaptureOnit = thisBoard;
        }
        isCaptured = true;
        boardToMakeCaptureOnit.getCell(coordinate).setPieceOnIt(null);
    }

    @Override
    public boolean isCaptured() {
        return isCaptured;
    }

    @Override
    public IPlayer getOwningPlayer() {
        return owningPlayer;
    }

    @Override
    public ICoordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(ICoordinate coordinate) {
        this.coordinate = coordinate;
    }


}