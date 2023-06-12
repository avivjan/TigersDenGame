package TigersDen.BL.BoardService.Contract;

public interface IObjectCreator {
    void createPieces() throws Exception;
    void createPlayers();
}
