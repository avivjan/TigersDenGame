package TigersDen.BL.BoardService.Contract;

import java.util.List;
import java.util.UUID;

import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public interface IPiece {
    UUID getID();
    void capture(IBoard boardToMakeCaptureOnit);
    IPlayer getOwningPlayer();
    ICoordinate getCoordinate();
    void setCoordinate(ICoordinate coordinate); 
    boolean isCaptured();
    List<MovingDetails> getOptionalMovements(IBoard board) throws Exception;
    IPiece clone();
}