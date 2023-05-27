package TigersDen.DAL.Contract;

import javax.swing.JButton;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;

public interface IBoardData {
    ICell getCell(ICoordinate coordinate) throws Exception;

    ICell getTigerDenCell();

    // ICell getSelectedCell();

    // void setSelectedCell(ICell cell);

    // void addPiece(IPiece piece, ICoordinate coordinate);
    
    int getCellSizeInPixels();

    int getNumOfCols();

    int getNumOfRows();

    void setButtonOfCell(ICoordinate coordinate, JButton button);

    void setButtonOfTigerDen(JButton button);


    // void addCell(ICell cell, Coordinate coordinate);

    // List<IPiece> getPieces();

    // void addPlayer(IPlayer player);

    // IPlayer getPlayer(String color);

    // List<ICell> getOptionalCells();
    
    // void addOptionCells(ICell optionCell);
    
}
