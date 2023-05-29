package TigersDen.BL.TurnManager.BussinessLogic;

import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.PlayerService.BussinesLogic.CpuPlayer;
import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class TurnManager implements ITurnManager {
    private List<IPlayer> players;
    private int currentPlayerIndex;

    public TurnManager() {
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
    public void initialize() {
        //TODO: move to config
        players.add(new HumanPlayer("aviv", "black"));
        players.add(new CpuPlayer("cpu", "white"));
    }
}