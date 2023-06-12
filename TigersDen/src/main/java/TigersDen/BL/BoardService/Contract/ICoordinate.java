package TigersDen.BL.BoardService.Contract;

public interface ICoordinate {
    int getRow();
    int getColumn();
    int getXInPixels();
    int getYInPixels();
    boolean isValidCoordinate();
    double getDistanceTo(ICoordinate coordinate);
    double getDistanceByRowAndColTo(ICoordinate coordinate);
    void setSpacial(boolean isSpacial);
    boolean isSpacial();
    boolean isOnBoard();
    boolean equalByRowAndCol(ICoordinate other);
    ICoordinate clone();
}
