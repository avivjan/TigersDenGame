package TigersDen.BL.ConfigurationService.DataModel;

import java.util.List;

public class ConfigurationData {
    private String assetsPath;
    private int cellSize;
    private int screenHeight;
    private int screenWidth;
    private int assetsSize;
    private int numOfRows;
    private int numOfCols;
    private List<PlayerDetails> playersDetails;
    private float pieceMovementSpeed;

    public String getAssetsPath() {
        return assetsPath;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getAssetsSize() {
        return assetsSize;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public List<PlayerDetails> getPlayersDetails() {
        return playersDetails;
    }

    public float getPieceMovementSpeed() {
        return pieceMovementSpeed;
    }
    
    @Override
    public String toString() {
        return "ConfigurationData [assetsPath=" + assetsPath + ", assetsSize=" + assetsSize + ", cellSize=" + cellSize
                + ", numOfCols=" + numOfCols + ", numOfRows=" + numOfRows + ", screenHeight=" + screenHeight
                + ", screenWidth=" + screenWidth + ", pieceMovementSpeed=" + pieceMovementSpeed + ", playersDetails="
                + playersDetails + "]";
    }
}
