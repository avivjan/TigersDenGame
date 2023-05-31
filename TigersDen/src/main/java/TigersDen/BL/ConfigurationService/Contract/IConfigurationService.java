package TigersDen.BL.ConfigurationService.Contract;

import java.util.List;

import TigersDen.BL.PlayerService.DataModel.PlayerDetails;

public interface IConfigurationService
{
    void init(String configPath);
    String getAssetsPath();
    int getCellSize();
    int getScreenHeight();
    int getScreenWidth();
    int getAssetsSize();
    int getNumOfRows();
    int getNumOfCols();
    List<PlayerDetails> getPlayersDetails();
    float GetPieceMovementSpeed();
}