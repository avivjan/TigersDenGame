package TigersDen.BL.BoardService.BussinessLogic;

import java.util.List;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public abstract class AbstractPiece implements IPiece {
    private ICoordinate coordinate;
    private boolean isCaptured;
    private IPlayer owningPlayer;
    protected IMovementService movingService;

    public AbstractPiece(ICoordinate coordinate2,  IPlayer owningPlayer){
        this.coordinate = coordinate2;
        this.owningPlayer = owningPlayer;
    }

    @Override
    public abstract List<ICell> getOptionalMovements(IBoard board) throws Exception;

    @Override
    public void startMoving(ICell cell) throws Exception {
        movingService.AddMovingDetails(new MovingDetails(this, cell));
        
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