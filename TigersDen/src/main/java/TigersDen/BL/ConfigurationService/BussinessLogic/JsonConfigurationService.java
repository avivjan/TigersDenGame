package TigersDen.BL.ConfigurationService.BussinessLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.PlayerService.DataModel.PlayerDetails;
import processing.core.PApplet;
import processing.data.JSONObject;

public class JsonConfigurationService implements IConfigurationService {
    private JSONObject jsonConfig;


    @Override
    public void init(String configPath) {
        jsonConfig = PApplet.loadJSONObject(new File(configPath));
    }

    @Override
    public String getAssetsPath() {
        return (String)jsonConfig.get("AssetsPath");
    }

    @Override
    public int getCellSize() {
        return (int)jsonConfig.get("CellSize");
    }

    @Override
    public int getScreenHeight() {
        return (int)jsonConfig.get("ScreenHeight");
    }

    @Override
    public int getScreenWidth() {
        return (int)jsonConfig.get("ScreenWidth");
    }

    @Override
    public int getAssetsSize() {
        return (int)jsonConfig.get("AssetsSize");
    }

    @Override
    public int getNumOfRows() {
        return (int)jsonConfig.get("NumOfRows");
    }

    @Override
    public int getNumOfCols() {
        return (int)jsonConfig.get("NumOfCols");
    }

    @Override
    public float GetPieceMovementSpeed() {
        return (int)jsonConfig.get("PieceMovementSpeed");
    }

    @Override
    public List<PlayerDetails> getPlayersDetails() {
        return new ArrayList<>();
    }

    

}