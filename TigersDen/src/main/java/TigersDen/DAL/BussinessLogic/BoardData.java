package TigersDen.DAL.BussinessLogic;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.Cell;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.DI.InjectorStorage;

public class BoardData implements IBoardData {

    private ICell[][] board;
    private ICell tigerDen;
    private ICell selectedCell;
    private List<IPiece> pieces;
    private int cellSizeInPixels;
    private int numOfCols;
    private int numOfRows;
    private List<ICell> optionCells;
    private boolean initialized = false;

    public BoardData() {
        selectedCell = null;

        this.pieces = new ArrayList<IPiece>();
        optionCells = new ArrayList<ICell>();
    }

    private void init() {
        IConfigurationService cs = InjectorStorage.getInjector().getInstance(IConfigurationService.class);
        this.numOfCols = cs.getNumOfCols();
        ;
        this.numOfRows = cs.getNumOfRows();
        ;
        this.cellSizeInPixels = cs.getCellSize();
        board = new ICell[numOfRows][numOfCols];
        createBoardCells();
        initialized = true;
    }

    @Override
    public ICell getCell(ICoordinate coordinate) throws IllegalArgumentException {
        if (!initialized) {
            init();
        }
        if (coordinate.isSpacial())
            return tigerDen;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();
        if (isValidCoordinate(row, col)) {
            return board[row][col];
        }
        return null;
    }

    @Override
    public ICell getSelectedCell() {
        if (!initialized) {
            init();
        }
        return selectedCell;
    }

    @Override
    public void setSelectedCell(ICell cell) {
        if (!initialized) {
            init();
        }
        selectedCell = cell;
    }

    @Override
    public void selectCell(ICell cell) {
        if (!initialized) {
            init();
        }
        selectedCell = cell;
    }

    @Override
    public void DeselectCellIfExists() {
        if (!initialized) {
            init();
        }
        selectedCell = null;
    }

    @Override
    public void addPiece(IPiece piece) {
        if (!initialized) {
            init();
        }
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
        if (!initialized) {
            init();
        }
        ICoordinate coordinate = cell.getCoordinate();
        if (coordinate.isSpacial()) {
            tigerDen = cell;
            return;
        }
        board[coordinate.getRow()][coordinate.getColumn()] = cell;
    }

    @Override
    public ICell getTigerDenCell() {
        if (!initialized) {
            init();
        }
        return tigerDen;
    }

    private boolean isValidCoordinate(int row, int col) {// ToDO: move to BL
        if (!initialized) {
            init();
        }
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }

    public int getCellSizeInPixels() {
        if (!initialized) {
            init();
        }
        return cellSizeInPixels;
    }

    public int getNumOfCols() {
        if (!initialized) {
            init();
        }
        return numOfCols;
    }

    public int getNumOfRows() {
        if (!initialized) {
            init();
        }
        return numOfRows;
    }

    public List<IPiece> getPieces() {
        if (!initialized) {
            init();
        }
        return pieces;
    }

    @Override
    public List<ICell> getOptionalCells() {
        if (!initialized) {
            init();
        }
        return optionCells;
    }

    @Override
    public void addOptionCells(ICell optionCell) {
        if (!initialized) {
            init();
        }
        optionCells.add(optionCell);
    }

    private void createBoardCells() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                board[i][j] = new Cell(CellStatus.None, false, Coordinate.createInstance(i, j, false), null);
            }
        }
        tigerDen = new Cell(CellStatus.None, false, Coordinate.createSpacialInstance(), null);
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

    @Override
    public IBoardData clone() {
        BoardData clone = new BoardData();
        clone.board = new ICell[numOfRows][numOfCols];
        clone.numOfCols = numOfCols;
        clone.numOfRows = numOfRows;
        clone.cellSizeInPixels = cellSizeInPixels;
        clone.pieces = new ArrayList<IPiece>();
        clone.optionCells = new ArrayList<ICell>();
        clone.tigerDen = tigerDen.clone();
        if (tigerDen.getPieceOnIt() != null) {
            clone.pieces.add(tigerDen.getPieceOnIt());
        }
        if (selectedCell != null) {
            clone.selectedCell = selectedCell.clone();
        }
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                ICell cell = board[i][j].clone();
                clone.board[i][j] = cell;
                if (cell.getPieceOnIt() != null) {
                    clone.pieces.add(cell.getPieceOnIt());
                }
                if (cell.getStatus() == CellStatus.Option) {
                    clone.optionCells.add(cell);
                }
            }
        }
        clone.initialized = initialized;
        return clone;
    }
}
