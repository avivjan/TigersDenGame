package TigersDen.BL.BoardService.BussinessLogic;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.DI.InjectorStorage;

public class Coordinate implements ICoordinate {
    private int x;
    private int y;
    private boolean isSpacial;

    private static boolean initialized = false;
    private static int cellSize;
    private static int numOfRows;
    private static int numOfCols;


    private Coordinate(int x, int y, boolean isSpacial) {
        this.x = x;
        this.y = y;
        this.isSpacial = isSpacial;
    }

    private static void init()
    {
        IConfigurationService cs = InjectorStorage.getInjector().getInstance(IConfigurationService.class);
        Coordinate.numOfCols = cs.getNumOfCols();
        Coordinate.numOfRows = cs.getNumOfRows();
        Coordinate.cellSize = cs.getCellSize();
        initialized = true;
    }

    public static Coordinate createSpacialInstance() {
        if (!initialized)
        {
            init();
        }
        return new Coordinate(getXInPixelForSpecialCell(), getYInPixelForSpecialCell(), true);
    }

    public static Coordinate createInstance(int x, int y, boolean inPixels) {
        if (!initialized)
        {
            init();
        }
        if (!isOnBoard(x, y, inPixels)) {
            throw new IllegalArgumentException("x and y must be on board");
        }
        if (inPixels) {
            if (isSpacialCoordinateByPixels(x, y)) {
                return createSpacialInstance();
            }
            return new Coordinate(x, y, false);

        } else {
            int row = x;//Naming purposes
            int col = y;
            if (isSpacialCoordinateByRowAndCol(row, col)) {
                return createSpacialInstance();
            }
            return new Coordinate(col*cellSize, (row+1)*cellSize, false);
        }

    }

    @Override
    public int getRow() {
        if (this.isSpacial) {
            return getRowForSpecialCell();
        }
        return (y-1) / cellSize;
    }

    @Override
    public int getColumn() {
        return x / cellSize;
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
        return x;
    }

    @Override
    public int getYInPixels() {
        return y;
    }

    public static void SetNumOfRows(int numOfRows) {
        Coordinate.numOfRows = numOfRows;
    }

    public static void SetNumOfCols(int numOfCols) {
        Coordinate.numOfCols = numOfCols;
    }

    @Override
    public String toString() {
        return "Row: " + getRow() + " Column: " + getColumn() +
                ", x: " + getXInPixels() + " y: " + getYInPixels();
    }

    @Override
    public boolean isValidCoordinate() {
        if (isSpacialCoordinateByPixels(x, y)) {
            return true;
        }
        return y >= 0 && y < numOfRows * cellSize && x >= 0 && x < numOfCols * cellSize;
    }

    @Override
    public double getDistanceTo(ICoordinate coordinate) {
        int xTarget = coordinate.getXInPixels();
        int yTarget = coordinate.getYInPixels();
        return Math.sqrt(Math.pow(x - xTarget, 2) + Math.pow(y - yTarget, 2));
    }

    public static boolean isOnBoard(int x, int y, boolean inPixels) {
        if (inPixels) {
            if (x >= 0 && x < numOfCols * cellSize && y >= 0 && y < (numOfRows + 1) * cellSize) {
                return true;
            }
            return isSpacialCoordinateByPixels(x, y);
        }
        else
        {
            if (x >= 0 && x < numOfCols && y >= 0 && y < numOfRows) {
                return true;
            }
            return isSpacialCoordinateByRowAndCol(x, y);
        }
    }

    public static  boolean isSpacialCoordinateByPixels(int x, int y) {
        return x == getXInPixelForSpecialCell() && y == getYInPixelForSpecialCell();
    }

    private static int getXInPixelForSpecialCell() {
        return numOfCols / 2 * cellSize;
    }
    private static int getYInPixelForSpecialCell() {
        return 0;
    }

    public static  boolean isSpacialCoordinateByRowAndCol(int x, int y) {
        return x == getColoumnForSpecialCell() && y == getRowForSpecialCell();
    }

    private static int getColoumnForSpecialCell() {
        return numOfCols / 2;
    }
    private static int getRowForSpecialCell() {
        return -1;
    }
}