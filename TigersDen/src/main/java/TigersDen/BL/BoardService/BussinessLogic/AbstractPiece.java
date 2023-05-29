package TigersDen.BL.BoardService.BussinessLogic;

import TigersDen.BL.BoardService.Contract.*;

public abstract class AbstractPiece implements IPiece {
    private ICoordinate coordinate;
    //private boolean isCaptured;
    //private IPlayer owningPlayer;
    //protected IMovingService movingService;

    public AbstractPiece(ICoordinate coordinate2){//, IPlayer owningPlayer, IMovingService movingSevice) {
        this.coordinate = coordinate2;
        //this.owningPlayer = owningPlayer;
        //this.movingService = movingSevice;
    }

    // @Override
    // public abstract List<ICell> getOptionalMovements(IBoard board) throws Exception;

    // @Override
    // public void startMoving(ICell cell) throws Exception {
    //     movingService.setMovingDetails(new MovingDetails(this, cell));
        
    //     if (this.coordinate.equals(cell.getCoordinate())) {
    //         throw new Exception("The piece is already in the target cell.");
    //     }
    // }

    // @Override
    // public void capture() {
    //     isCaptured = true;
    // }

    // @Override
    // public boolean isCaptured() {
    //     return isCaptured;
    // }


    // @Override
    // public IPlayer getOwningPlayer() {
    //     return owningPlayer;
    // }

    @Override
    public ICoordinate getCoordinate() {
        return coordinate;
    }
   
    
    @Override
    public void setCoordinate(ICoordinate coordinate) {
        this.coordinate = coordinate;
    }

}