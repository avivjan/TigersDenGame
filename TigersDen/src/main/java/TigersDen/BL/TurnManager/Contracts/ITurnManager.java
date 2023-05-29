package TigersDen.BL.TurnManager.Contracts;

import java.awt.print.Paper;

import TigersDen.BL.PlayerService.Contract.IPlayer;

public interface ITurnManager {
    IPlayer getPlayerInTurn();
    void setNextPlayerInTurn();
}
