package TigersDen.BL.ConfigurationService.Contract;

import java.util.List;

import TigersDen.BL.ConfigurationService.DataModel.PlayerDetails;

public interface IConfigurationService {
    String getAssetsPath();

    int getCellSize();

    int getScreenHeight();

    int getScreenWidth();

    int getAssetsSize();

    int getNumOfRows();

    int getNumOfCols();

    List<PlayerDetails> getPlayersDetails();

    int GetPieceMovementSpeed();

    void addPlayerDetails(PlayerDetails playerDetails);
}