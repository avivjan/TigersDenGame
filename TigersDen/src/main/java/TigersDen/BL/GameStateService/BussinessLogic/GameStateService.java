package TigersDen.BL.GameStateService.BussinessLogic;

import TigersDen.BL.GameStateService.Contract.IGameStateService;
import TigersDen.BL.GameStateService.DataModel.GameState;

public class GameStateService implements IGameStateService {
    private GameState gameState;

    public GameStateService() {
        gameState = GameState.MAIN_MENU;
        System.out.println("GameStateService created");
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }
}
