package TigersDen.BL.BoardValidator.Contract;


public interface IBoardValidator {
    String getWinnerRole() throws Exception;
    void updateWinner() throws Exception;
}
