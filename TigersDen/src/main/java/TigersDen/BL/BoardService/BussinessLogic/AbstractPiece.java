package XXLChess.Implementations;

import XXLChess.MovingDetails;
import XXLChess.Interfaces.IBoard;
import XXLChess.Interfaces.ICell;
import XXLChess.Interfaces.ICoordinate;
import XXLChess.Interfaces.IMovingService;
import XXLChess.Interfaces.IPiece;
import XXLChess.Interfaces.IPlayer;

import java.util.List;

public abstract class AbstractPiece implements IPiece {
    private ICoordinate coordinate;
    private boolean isCaptured;
    private IPlayer owningPlayer;
    protected IMovingService movingService;

    public AbstractPiece(ICoordinate coordinate2, IPlayer owningPlayer, IMovingService movingSevice) {
        this.coordinate = coordinate2;
        this.owningPlayer = owningPlayer;
        this.movingService = movingSevice;
    }

    @Override
    public abstract List<ICell> getOptionalMovements(IBoard board) throws Exception;

    @Override
    public void startMoving(ICell cell) throws Exception {
        movingService.setMovingDetails(new MovingDetails(this, cell));
        
        if (this.coordinate.equals(cell.getCoordinate())) {
            throw new Exception("The piece is already in the target cell.");
        }
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