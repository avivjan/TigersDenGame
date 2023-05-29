package TigersDen.BL.BoardService.Contract;

import java.util.List;

import TigersDen.BL.BoardService.Model.ICell;

public interface IPiece {
    List<ICell> getOptionalMovements() throws Exception;
    //void startMoving(ICell cell) throws Exception;
    //void capture();
    //IPlayer getOwningPlayer();
    ICoordinate getCoordinate();
    void setCoordinate(ICoordinate coordinate); 
    //boolean isCaptured();
}