package TigersDen.BL.BoardService.BussinessLogic;

import TigersDen.BL.BoardService.Contract.ICoordinate;

public class Coordinate implements ICoordinate {
    private int row;
    private int column;
    private boolean isSpacial;
    private static int cellSize = 100;
    private static int numOfRows = 9;;// TODO: handle hard coded values
    private static int numOfCols = 9;// TODO: handle hard coded values

    private Coordinate(int row, int column, boolean isSpacial) {
            this.row = row;
            this.column = column;
            this.isSpacial = isSpacial;
    }

    public static Coordinate createSpacialInstance() {
        return new Coordinate(-1, numOfCols / 2, true);
    }

    public static Coordinate createInstance(int x, int y, boolean inPixels) {
        if (!isOnBoard(x, y, inPixels)) {
            throw new IllegalArgumentException("x and y must be on board");
        }
        if (inPixels) {
            int row = (y / cellSize)-1;
            int column = x / cellSize;
            return new Coordinate(row, column, false);

        } else {
            return new Coordinate(x, y, false);
        }

    }

    @Override
    public int getRow() {
        if (this.isSpacial) {
            return -1;
        }
        return row;
    }

    @Override
    public void setRow(int row) {
        if (row < -1 || row >= numOfRows) {
            throw new IllegalArgumentException("row must be between 0 and " + numOfRows);
        }
        this.row = row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setColumn(int column) {
        if (column < 0 || column >= numOfCols) {
            throw new IllegalArgumentException("column must be between 0 and " + numOfCols);
        }
        this.column = column;
    }

    @Override
    public boolean isSpacial() {
        return isSpacial;
    }

    @Override
    public void setSpacial(boolean isSpacial) {
        this.isSpacial = isSpacial;
    }

    @Override
    public int getXInPixels() {
        return column * cellSize;
    }

    @Override
    public int getYInPixels() {
        return (row + 1) * cellSize;
    }

    public static void SetNumOfRows(int numOfRows) {
        Coordinate.numOfRows = numOfRows;
    }

    public static void SetNumOfCols(int numOfCols) {
        Coordinate.numOfCols = numOfCols;
    }

    @Override
    public String toString() {
        return "Row: " + row + " Column: " + column;
    }

    @Override
    public boolean isValidCoordinate() {
        return row >= 0 && row < numOfRows && column >= 0 && column < numOfCols;
    }

    @Override
    public double getDistanceTo(ICoordinate coordinate) {
        int x1 = getXInPixels();
        int y1 = getYInPixels();
        int x2 = coordinate.getXInPixels();
        int y2 = coordinate.getYInPixels();
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static boolean isOnBoard(int x, int y, boolean inPixels) {
        if (inPixels) {
            return x >= 0 && x < numOfCols * cellSize && y >= 0 && y < (numOfRows+1) * cellSize;
        }
        return x >= 0 && x < numOfCols && y >= 0 && y < numOfRows;
    }

}