package TigersDen.BL.TurnManager.BussinessLogic;

import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class TurnManager implements ITurnManager {
    private IPlayer[] players;
    private int currentPlayerIndex;

    public TurnManager(IPlayer[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    @Override
    public IPlayer getPlayerInTurn() {
        return players[currentPlayerIndex];
    }

    @Override
    public void setNextPlayerInTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }
}