package TigersDen.BL.BoardValidator.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class BoardValidator implements IBoardValidator {

    private final IBoard board;

    @Inject
    public BoardValidator(IBoard board,ITurnManager turnManager) {
        this.board = board;
    }

    @Override
    public String getWinnerName() throws Exception {
        if (isTigerWinner()) {
            return "tiger";
        } else if (isPawnsWinner()) {
            return "pawns";
        }
        return null;
    }

    private boolean isPawnsWinner() throws Exception {
        for (IPiece piece : board.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("tiger"));
            {
                if(piece.getOptionalMovements(board).size() == 0)
                {
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
}