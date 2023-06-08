package TigersDen.BL.ConfigurationService.BussinessLogic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.ConfigurationService.DataModel.ConfigurationData;
import TigersDen.BL.ConfigurationService.DataModel.PlayerDetails;

public class JsonConfigurationService implements IConfigurationService {
    private final String jsonFilePath = "src/main/resources/config.json";
    private ConfigurationData configurationData;

    public JsonConfigurationService() throws IOException {
        try {
            String jsonString = readJsonFile(jsonFilePath);
            Gson gson = new GsonBuilder().create();
            this.configurationData = gson.fromJson(jsonString, ConfigurationData.class);

        } catch (JsonSyntaxException e) {
            throw new IOException("Invalid JSON format", e);
        }
    }

    private String readJsonFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    @Override
    public String getAssetsPath() {
        return configurationData.getAssetsPath();
    }

    @Override
    public int getCellSize() {
        return configurationData.getCellSize();
    }

    @Override
    public int getScreenHeight() {
        return configurationData.getScreenHeight();
    }

    @Override
    public int getScreenWidth() {
        return configurationData.getScreenWidth();
    }

    @Override
    public int getAssetsSize() {
        return configurationData.getAssetsSize();
    }

    @Override
    public int getNumOfRows() {
        return configurationData.getNumOfRows();
    }

    @Override
    public int getNumOfCols() {
        return configurationData.getNumOfCols();
    }

    @Override
    public List<PlayerDetails> getPlayersDetails() {
        return configurationData.getPlayersDetails();
    }

    @Override
    public float GetPieceMovementSpeed() {
        return configurationData.getPieceMovementSpeed();
    }
}
