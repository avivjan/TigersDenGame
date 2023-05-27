package TigersDen.BL.BoardService.Contract;

public interface ICoordinate {
    int getRow();
    void setRow(int row);
    int getColumn();
    void setColumn(int column);
    int getXInPixels();
    int getYInPixels();
    boolean isValidCoordinate();
    double getDistanceTo(ICoordinate coordinate);
    void setSpacial(boolean isSpacial);
    boolean isSpacial();
}
