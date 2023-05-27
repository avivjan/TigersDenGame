package TigersDen.BL.BoardService.BussinessLogic;

import TigersDen.BL.BoardService.Contract.ICoordinate;

public class Coordinate implements ICoordinate {
    private int row;
    private int column;
    private boolean isSpacial;
    private static int cellSize = 48;
    private static int numOfRows;
    private static int numOfCols;;
    

    private Coordinate(int row, int column, boolean isSpacial) {
        this.row = row;
        this.column = column;
        this.isSpacial = isSpacial;
    }

    public static Coordinate createInstance(int x, int y, boolean inPixels, boolean isSpacial) {
        if (isSpacial) {
            return new Coordinate(-1, -1, true);
        }
        if (!inPixels) {
            return new Coordinate(x, y, false);
        }

        if (!isOnBoard(x, y)) {
            return null;
        }
        int row = y / cellSize;
        int column = x / cellSize;
        return new Coordinate(column, row, false);
    }

    @Override
    public int getRow() {
        if (this.isSpacial)
        {
            return -1;
        }
        return row;
    }

    @Override
    public void setRow(int row) {
        if (row < 0 || row >= numOfRows) {
            throw new IllegalArgumentException("row must be between 0 and " + numOfRows);
        }
        this.row = row;
    }

    @Override
    public int getColumn() {
        if (this.isSpacial)
        {
            return -1;
        }
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
        if (this.isSpacial)
        {
            return 0;
        }
        return (row + 1) * cellSize;
    }

    @Override
    public int getYInPixels() {
        if (this.isSpacial)
        {
            return numOfCols * cellSize / 2 - (cellSize / 2);
        }
        return column * cellSize;
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
    

    public static boolean isOnBoard(int getXInPixels, int getYInPixels) {
        return getXInPixels >= 0 && getXInPixels < numOfCols * cellSize && getYInPixels >= 0
                && getYInPixels < numOfRows * cellSize;
    }

}