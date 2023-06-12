package TigersDen.BL.BoardService.BussinessLogic;

import java.util.List;
import java.util.UUID;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public abstract class AbstractPiece implements IPiece {
    private IPlayer owningPlayer;
    protected boolean isCaptured;
    protected UUID id;
    protected ICoordinate coordinate;
    protected IMovementService movementService;

    public AbstractPiece(ICoordinate coordinate2, IPlayer owningPlayer, IMovementService ms) {
        this.movementService = ms;
        this.coordinate = coordinate2;
        this.owningPlayer = owningPlayer;
        isCaptured = false;
        id = UUID.randomUUID();
    }

    @Override
    public abstract List<ICell> getOptionalMovements(IBoard board) throws Exception;
    @Override
    public abstract IPiece clone();

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public void capture() {
        isCaptured = true;
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