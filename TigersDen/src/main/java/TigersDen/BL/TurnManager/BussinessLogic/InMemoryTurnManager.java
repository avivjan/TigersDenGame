package TigersDen.BL.TurnManager.BussinessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
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
    public void setNextPlayerInTurn() throws Exception {
        try {
            String winner = boardValidator.getWinnerRole();
            if (winner != null) {
                boardValidator.updateWinner();
                return;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            IPlayer playerInTurn = getPlayerInTurn();
            if (playerInTurn.isHuman() == false) {
                playerInTurn.play(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
        Collections.sort(players);
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