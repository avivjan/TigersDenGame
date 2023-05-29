package TigersDen.BL.TurnManager.Contracts;

import java.awt.print.Paper;
import java.util.List;

import TigersDen.BL.PlayerService.Contract.IPlayer;

public interface ITurnManager {
    void SetPlayers(List<IPlayer> players);
    IPlayer getPlayerInTurn();
    void setNextPlayerInTurn();
}
