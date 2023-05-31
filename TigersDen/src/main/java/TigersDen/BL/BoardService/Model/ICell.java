package TigersDen.BL.BoardService.Model;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;

public interface ICell {
    CellStatus getStatus();
    boolean isSelected();
    boolean canBeSelected();
    IPiece getPieceOnIt();
    void setPieceOnIt(IPiece piece);
    ICoordinate getCoordinate();
    void setStatus(CellStatus cs);
}

