package TigersDen.BL.TurnManager.BussinessLogic;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
import TigersDen.BL.PlayerService.BussinesLogic.CpuPlayer;
import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class InMemoryTurnManager implements ITurnManager {
    private List<IPlayer> players;
    private int currentPlayerIndex;
    private IBoardValidator boardValidator;

    @Inject
    public InMemoryTurnManager(IBoardValidator boardValidator) {
        super();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.boardValidator = boardValidator;
    }

    @Override
    public IPlayer getPlayerInTurn() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public void setNextPlayerInTurn() {
        try {
            String winner = boardValidator.getWinnerName();
            if (winner != null) {
                System.out.println("The winner is: " + boardValidator.getWinnerName());
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            if (getPlayerInTurn() instanceof CpuPlayer) {
                boolean isTurnOver = getPlayerInTurn().play(null);
                if (isTurnOver)
                {
                    setNextPlayerInTurn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public IPlayer getPlayerByRole(String string) {
        IPlayer resultPlayer = null;
        for (IPlayer player : players) {
            if (player.getRole().equals(string)) {
                if (resultPlayer == null) {
                    resultPlayer = player;
                } else {
                    throw new RuntimeException("There are more than one player with the same role");
                }
            }
        }
        if (resultPlayer == null) {
            throw new RuntimeException("There is no player with the role: " + string);
        }
        return resultPlayer;
    }
}