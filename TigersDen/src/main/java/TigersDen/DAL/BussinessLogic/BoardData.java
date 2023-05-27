package TigersDen.DAL.BussinessLogic;

import java.util.UUID;

import javax.jws.soap.InitParam;
import javax.swing.JButton;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Cell;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.Contract.IBoardData;

public class BoardData implements IBoardData {
    private ICell[][] board;
    private ICell tigerDen;
    // private ICell selectedCell;
    // private List<IPiece> pieces;
    // private List<IPlayer> players;
    private int cellSizeInPixels;
    private int numOfCols;
    private int numOfRows;
    // private List<ICell> optionCells;

    public BoardData(int numOfCols, int numOfrows, int cellSize) {
        // selectedCell = null;
        this.numOfCols = numOfCols;
        this.numOfRows = numOfrows;
        this.cellSizeInPixels = cellSize;
        board = new ICell[numOfrows][numOfCols];
        createBoardCells();
        tigerDen = new Cell(CellStatus.None, false, Coordinate.createInstance(-1, -1, false, true), null);
        // this.pieces = new ArrayList<IPiece>();
        // this.players = new ArrayList<IPlayer>();
        // optionCells = new ArrayList<ICell>();
    }

    public ICell getCell(ICoordinate coordinate) throws Exception {
    int row = coordinate.getRow();
    int col = coordinate.getColumn();
    if (isValidCoordinate(row, col)) {
    return board[row][col];
    }
    throw new Exception("invalid coordinate");
    }

    // public void addPlayer(IPlayer player) {
    // players.add(player);
    // }

    // @Override
    // public IPlayer getPlayer(String color) {
    // for (IPlayer player : players) {
    // if (player.getColor().equals(color)) {
    // return player;
    // }
    // }
    // throw new IllegalArgumentException("No player with color " + color + "
    // found");
    // }

    // @Override
    // public ICell getSelectedCell() {
    // return selectedCell;
    // }

    // @Override
    // public void setSelectedCell(ICell cell) {
    // selectedCell = cell;
    // }

    // @Override
    // public void addPiece(IPiece piece, ICoordinate coordinate) {
    // pieces.add(piece);
    // board[coordinate.getRow()][coordinate.getColumn()].setPieceOnIt(piece);
    // }

    // @Override
    // public void addCell(ICell cell, Coordinate coordinate) {
    // board[coordinate.getRow()][coordinate.getColumn()] = cell;
    // }

    @Override
    public ICell getTigerDenCell() {
        return tigerDen;
    }
    private boolean isValidCoordinate(int row, int col) { //ToDO: move to BL
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

    @Override
    public void setButtonOfCell(ICoordinate coordinate, JButton button) {
        board[coordinate.getRow()][coordinate.getColumn()].setButtonOfCell(button);
    }

    @Override
    public void setButtonOfTigerDen(JButton button) {
        tigerDen.setButtonOfCell(button);
    }

    // public List<IPiece> getPieces() {
    // return pieces;
    // }

    // @Override
    // public List<ICell> getOptionalCells() {
    // return optionCells;
    // }

    // @Override
    // public void addOptionCells(ICell optionCell) {
    // optionCells.add(optionCell);
    // }

    private void createBoardCells() {
        for (int i = 0; i <numOfRows; i++)
        {
            for (int j = 0; j < numOfCols; j++)
            {
                board[i][j] = new Cell(CellStatus.None, false, Coordinate.createInstance(i, j, false, false), null);
            }
        }
    }

}
