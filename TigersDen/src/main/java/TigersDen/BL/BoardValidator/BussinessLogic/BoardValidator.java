package TigersDen.BL.BoardValidator.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
import TigersDen.BL.GameStateService.BussinessLogic.GameStateService;
import TigersDen.BL.GameStateService.DataModel.GameState;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class BoardValidator implements IBoardValidator {

    private final IBoard board;
    private GameStateService gameStateService;

    @Inject
    public BoardValidator(IBoard board, ITurnManager turnManager, GameStateService gameStateService) {
        this.board = board;
        this.gameStateService = gameStateService;
    }

    @Override
    public String getWinnerRole() throws Exception {
        if (isTigerWinner()) {
            return "tiger";
        } else if (isPawnsWinner()) {
            return "pawns";
        }
        return null;
    }

    private boolean isPawnsWinner() throws Exception {
        for (IPiece piece : board.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("tiger"))
                ;
            {
                if (piece.getOptionalMovements(board).size() == 0) {
                    return true;
                }
                return false;
            }
        }
        throw new Exception("there is no player with the role of tiger");
    }

    private boolean isTigerWinner() {
        int alivePawnsCount = 0;
        for (IPiece piece : board.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("pawns") && !piece.isCaptured()) {
                alivePawnsCount++;
            }
        }
        return alivePawnsCount == 0;
    }

    @Override
    public void updateWinner() throws Exception {
        String winnerRole = getWinnerRole();
        if (winnerRole == null) {
            throw new Exception("UpdateWinner: there is no winner");
        }
        if (winnerRole == "tiger") {
            gameStateService.setGameState(GameState.TIGER_WIN_SCREEN);
        } else {
            gameStateService.setGameState(GameState.PAWNS_WIN_SCREEN);
        }
        
    }
}