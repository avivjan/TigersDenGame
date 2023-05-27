package TigersDen.BL.BoardService.BussinessLogic;

import javax.swing.JButton;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.BussinessLogic.BoardData;
import TigersDen.DAL.Contract.IBoardData;

public class Board implements IBoard {
    private IBoardData boardData;

    @Inject
    public Board(IBoardData boardData) {
        this.boardData = boardData;
    }

    @Override
    public int getNumOfRows() {
        return boardData.getNumOfRows();
    }

    @Override
    public int getNumOfCols() {
        return boardData.getNumOfCols();
    }

    @Override
    public int getCellSizeInPixels() {
        return boardData.getCellSizeInPixels();
    }

    @Override
    public void setButtonOfCell(ICoordinate cor, JButton button) {
        if (cor.isSpacial()){
            boardData.setButtonOfTigerDen(button);
        }
        else
        {
            boardData.setButtonOfCell(cor, button);
        }
    }

    @Override
    public ICell getCell(ICoordinate coordinate) throws Exception {
        return boardData.getCell(coordinate);
    }

    @Override
    public ICell getTigerDenCell() {
        return boardData.getTigerDenCell();
    }
}
