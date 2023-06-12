package TigersDen.BL.GameStateService.Contract;

import TigersDen.BL.GameStateService.DataModel.GameState;

public interface IGameStateService {
    void setGameState(GameState gameState);
    GameState getGameState();

}
