package TigersDen.BL.TurnManager.Contracts;
import java.util.List;

import TigersDen.BL.PlayerService.Contract.IPlayer;

public interface ITurnManager {
    IPlayer getPlayerInTurn();
    void setNextPlayerInTurn() throws Exception;
    List<IPlayer> getAllPlayers();
    List<IPlayer> getHumanPlayers();
    List<IPlayer> getCPUPlayers();
    void addPlayer(IPlayer player);
    IPlayer getPlayerByRole(String string);
}
