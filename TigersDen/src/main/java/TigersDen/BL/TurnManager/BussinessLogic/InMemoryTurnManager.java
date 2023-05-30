package TigersDen.BL.TurnManager.BussinessLogic;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class InMemoryTurnManager implements ITurnManager {
    private List<IPlayer> players;
    private int currentPlayerIndex;


    

    public InMemoryTurnManager() {
        super();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }
    

    @Override
    public IPlayer getPlayerInTurn() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public void setNextPlayerInTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }


    
    @Override
    public List<IPlayer> getAllPlayers() {
        return players;
    }

    @Override
    public List<IPlayer> getHumanPlayers() {
        List<IPlayer> humanPlayers = new ArrayList<>();
        for (IPlayer player : players) {
            if (player instanceof HumanPlayer) {
                humanPlayers.add(player);
            }
        }
        return humanPlayers;
    }

    @Override
    public List<IPlayer> getCPUPlayers() {
        List<IPlayer> cpuPlayers = new ArrayList<>();
        for (IPlayer player : players) {
            if (!(player instanceof HumanPlayer)) {
                cpuPlayers.add(player);
            }
        }
        return cpuPlayers;
    }
    @Override
    public void addPlayer(IPlayer player) {
        players.add(player);
    }
}