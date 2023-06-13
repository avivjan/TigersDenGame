package TigersDen.BL.BoardValidator.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
import TigersDen.BL.GameStateService.Contract.IGameStateService;
import TigersDen.BL.GameStateService.DataModel.GameState;

public class BoardValidator implements IBoardValidator {
    private IBoard realBoard;
    private IGameStateService gameStateService;

    @Inject
    public BoardValidator(IBoard board, IGameStateService gameStateService) {
        this.realBoard = board;
        this.gameStateService = gameStateService;
    }

    @Override
    public String getWinnerRole(IBoard board) throws Exception {
        if (isTigerWinner(board)) {
            return "tiger";
        } else if (isPawnsWinner(board)) {
            return "pawns";
        }
        return null;
    }

    @Override
    public String getWinnerRole() throws Exception {
        if (isTigerWinner(realBoard)) {
            return "tiger";
        } else if (isPawnsWinner(realBoard)) {
            return "pawns";
        }
        return null;
    }
    
    private boolean isPawnsWinner(IBoard board) throws Exception {
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

    private boolean isTigerWinner(IBoard board) {
        int alivePawnsCount = 0;
        for (IPiece piece : board.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("pawns") && !piece.isCaptured()) {
                alivePawnsCount++;
            }
        }
        return alivePawnsCount == 0;
    }

    @Override
    public void updateWinner(IBoard board, IGameStateService gameStateService) throws Exception {
        String winnerRole = getWinnerRole(board);
        if (winnerRole == null) {
            throw new Exception("UpdateWinner: there is no winner");
        }
        if (winnerRole == "tiger") {
            gameStateService.setGameState(GameState.TIGER_WIN_SCREEN);
        } else {
            gameStateService.setGameState(GameState.PAWNS_WIN_SCREEN);
        }
    }

    @Override
    public void updateWinner() throws Exception {
        String winnerRole = getWinnerRole(realBoard);
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