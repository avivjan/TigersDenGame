package TigersDen.BL.AI.Contract;

import TigersDen.BL.MovementService.DataModel.MovingDetails;

public interface ITigerDenBrain {
    MovingDetails minimax(int depth, boolean isMaximizingPlayer);
}
