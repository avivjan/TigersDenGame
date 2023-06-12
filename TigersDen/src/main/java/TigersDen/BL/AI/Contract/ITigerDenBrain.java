package TigersDen.BL.AI.Contract;

import TigersDen.BL.AI.DataModel.Move;
import TigersDen.BL.BoardService.Contract.IBoard;

public interface ITigerDenBrain {
    Move minimax(IBoard boardToMakeMoveOn, int depth, boolean isMaximizingPlayer) throws Exception;
}
