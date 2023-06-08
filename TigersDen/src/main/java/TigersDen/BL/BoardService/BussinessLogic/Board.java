package TigersDen.BL.BoardService.BussinessLogic;

import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.Contract.IBoardData;

public class Board implements IBoard {
    private IBoardData boardData;

    @Inject
    public Board(IBoardData boardData) {
        this.boardData = boardData;
        boardData.getCellSizeInPixels();
        Coordinate.SetNumOfRows(boardData.getNumOfRows());
        Coordinate.SetNumOfCols(boardData.getNumOfCols());
    }

    @Override
    public ICell getCell(ICoordinate coordinate) throws Exception {
        return boardData.getCell(coordinate);
    }

    @Override
    public ICell getSelectedCell() {
        return boardData.getSelectedCell();
    }

    @Override
    public void setSelectedCell(ICell cell) {
        boardData.setSelectedCell(cell);
    }

    @Override
    public int getNumOfCols() {
        return boardData.getNumOfCols();
    }

    @Override
    public int getNumOfRows() {
        return boardData.getNumOfRows();
    }

    @Override
    public void DeselectCellIfExists() {
        try {
            ICell selectedCell = getSelectedCell();
            if (selectedCell == null) {
                return;
            }
            for (ICell optional : boardData.getOptionalCells()) {
                optional.setStatus(CellStatus.None);
            }

            selectedCell.setStatus(CellStatus.None);
            setSelectedCell(null);
            System.out.println("Cell " + selectedCell.getCoordinate() + " deselected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectCell(ICell cell) {
        try {
            DeselectCellIfExists();
            cell.setStatus(CellStatus.Selected);
            setSelectedCell(cell);
            for (ICell optionalCell : cell.getPieceOnIt().getOptionalMovements(this)) {
                if (optionalCell.getPieceOnIt() != null && optionalCell.getPieceOnIt().getOwningPlayer() != this) {
                    optionalCell.setStatus(CellStatus.OptionWithCapture);
                    System.out.println("Cell " + optionalCell.getCoordinate() + " is an option with capture");
                    continue;
                }
                optionalCell.setStatus(CellStatus.Option);
                boardData.addOptionCells(optionalCell);
                System.out.println("Cell " + optionalCell.getCoordinate() + " is an option");
            }

            System.out.println("Cell " + cell.getCoordinate() + "selected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOptionCells(ICell optionCell) {
        boardData.addOptionCells(optionCell);
    }

    @Override
    public void addCell(ICell cell) {
        boardData.addCell(cell);
    }

    @Override
    public ICell getTigerDenCell() {
        return boardData.getTigerDenCell();
    }

    @Override
    public void addPiece(IPiece piece) throws Exception {
        boardData.addPiece(piece);
    }

    @Override
    public List<IPiece> getPieces() {
        return boardData.getPieces();
    }

    @Override
    public List<ICell> getCells() {
        return boardData.getCells();
    }
}