package TigersDen.DAL.Contract;

import java.util.List;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;

public interface IBoardData {
    ICell getCell(ICoordinate coordinate);

    ICell getTigerDenCell();

    ICell getSelectedCell();

    void setSelectedCell(ICell cell);

    void addPiece(IPiece piece);
    
    int getCellSizeInPixels();

    int getNumOfCols();

    int getNumOfRows();

    void addCell(ICell cell);

    List<IPiece> getPieces();

    List<ICell> getOptionalCells();
    
    void addOptionCells(ICell optionCell);

    void selectCell(ICell cell);
    
    void DeselectCellIfExists(); 

    List<ICell> getCells();

    IBoardData clone();
    
}
