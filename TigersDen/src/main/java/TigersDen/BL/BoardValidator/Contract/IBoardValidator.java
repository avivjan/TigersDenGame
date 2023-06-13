package TigersDen.BL.BoardValidator.Contract;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.GameStateService.Contract.IGameStateService;

public interface IBoardValidator {
    String getWinnerRole(IBoard board) throws Exception;
    String getWinnerRole() throws Exception;


    void updateWinner() throws Exception;
    void updateWinner(IBoard board, IGameStateService gameStateService) throws Exception;
}
