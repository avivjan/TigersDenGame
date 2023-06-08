package TigersDen.BL.BoardService.Contract;

import java.util.List;

import TigersDen.BL.BoardService.Model.ICell;

public interface IBoard {
    ICell getCell(ICoordinate coordinate) throws Exception;

    ICell getTigerDenCell();

    ICell getSelectedCell();

    void setSelectedCell(ICell cell);

    void selectCell(ICell cell);

    void DeselectCellIfExists();

    void addOptionCells(ICell optionCell);

    int getNumOfRows();

    int getNumOfCols();

    List<IPiece> getPieces();

    void addPiece(IPiece piece) throws Exception;

    void addCell(ICell cell);

    List<ICell> getCells();
}
