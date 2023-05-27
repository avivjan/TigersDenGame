package TigersDen.BL.BoardService.Contract;

import javax.swing.JButton;
import TigersDen.BL.BoardService.Model.ICell;

public interface IBoard {
    ICell getCell(ICoordinate coordinate) throws Exception;
    ICell getTigerDenCell();
    
    //ICell getSelectedCell();
    //void setSelectedCell(ICell cell);
 
    //float getMaxMoveDuration();
    //float getMovingSpeed();
    //void selectCell(ICell cell);
    //void DeselectCellIfExists();
    //void addOptionCells(ICell optionCell);
    int getNumOfRows();
    int getNumOfCols();
    int getCellSizeInPixels();
    void setButtonOfCell(ICoordinate coordinate, JButton button);

}
