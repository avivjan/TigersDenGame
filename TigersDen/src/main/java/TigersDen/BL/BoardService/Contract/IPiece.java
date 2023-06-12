package TigersDen.BL.BoardService.Contract;

import java.util.List;
import java.util.UUID;

import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public interface IPiece {
    UUID getID();
    void capture();
    IPlayer getOwningPlayer();
    ICoordinate getCoordinate();
    void setCoordinate(ICoordinate coordinate); 
    boolean isCaptured();
    List<ICell> getOptionalMovements(IBoard board) throws Exception;
    IPiece clone();
}