package TigersDen.DAL.BussinessLogic;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.Cell;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.Contract.IBoardData;

public class BoardData implements IBoardData {

    private ICell[][] board;
    private ICell tigerDen;
    private ICell selectedCell;
    private List<IPiece> pieces;
    private int cellSizeInPixels;
    private int numOfCols;
    private int numOfRows;
    private List<ICell> optionCells;

    public BoardData(int numOfCols, int numOfrows, int cellSize) {
        selectedCell = null;
        this.numOfCols = numOfCols;
        this.numOfRows = numOfrows;
        this.cellSizeInPixels = cellSize;
        board = new ICell[numOfrows][numOfCols];
        this.pieces = new ArrayList<IPiece>();
        optionCells = new ArrayList<ICell>();
        createBoardCells();
    }

    @Override
    public ICell getCell(ICoordinate coordinate) throws IllegalArgumentException {
        if (coordinate.isSpacial())
            return tigerDen;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();
        if (isValidCoordinate(row, col)) {
            return board[row][col];
        }
        throw new IllegalArgumentException("Invalid coordinate");
    }

    @Override
    public ICell getSelectedCell() {
        return selectedCell;
    }

    @Override
    public void setSelectedCell(ICell cell) {
        selectedCell = cell;
    }

    @Override
    public void selectCell(ICell cell) {
        selectedCell = cell;
    }

    @Override
    public void DeselectCellIfExists() {
        selectedCell = null;
    }

    @Override
    public void addPiece(IPiece piece) {
        ICoordinate coordinate = piece.getCoordinate();
        pieces.add(piece);
        if (coordinate.isSpacial()) {
            tigerDen.setPieceOnIt(piece);
        } else {
            board[coordinate.getRow()][coordinate.getColumn()].setPieceOnIt(piece);
        }
    }

    @Override
    public void addCell(ICell cell) {
        ICoordinate coordinate = cell.getCoordinate();
        if(coordinate.isSpacial())
        {
            tigerDen = cell;
            return;
        }
        board[coordinate.getRow()][coordinate.getColumn()] = cell;
    }

    @Override
    public ICell getTigerDenCell() {
        return tigerDen;
    }

    private boolean isValidCoordinate(int row, int col) { // ToDO: move to BL
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }

    public int getCellSizeInPixels() {
        return cellSizeInPixels;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public List<IPiece> getPieces() {
        return pieces;
    }

    @Override
    public List<ICell> getOptionalCells() {
        return optionCells;
    }

    @Override
    public void addOptionCells(ICell optionCell) {
        optionCells.add(optionCell);
    }

    private void createBoardCells() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                board[i][j] = new Cell(CellStatus.None, false, Coordinate.createInstance(i, j, false), null);
            }
        }
        tigerDen =  new Cell(CellStatus.None, false, Coordinate.createSpacialInstance(), null);
    }

    @Override
    public List<ICell> getCells() {
        List<ICell> cells = new ArrayList<ICell>();
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                cells.add(board[i][j]);
            }
        }
        cells.add(tigerDen);
        return cells;
    }
}
