package TigersDen.BL.BoardService.Contract;

public interface IObjectCreator {
    void createBoard() throws Exception;
    void createPieces();
    void createPlayers();
   
}
