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
            checkConfigurationData();

        } catch (JsonSyntaxException e) {
            throw new IOException("Invalid JSON format", e);
        }
    }

    private void checkConfigurationData() {
        if (configurationData == null)
            throw new IllegalArgumentException("Configuration data is null");
        if (configurationData.getAssetsPath() == null || configurationData.getAssetsPath().isEmpty())
            throw new IllegalArgumentException("Assets path is empty");
        if (configurationData.getCellSize() <= 0)
            throw new IllegalArgumentException("Cell size is not positive");
        if (configurationData.getScreenHeight() <= 0)
            throw new IllegalArgumentException("Screen height is not positive");
        if (configurationData.getScreenWidth() <= 0)
            throw new IllegalArgumentException("Screen width is not positive");
        if (configurationData.getAssetsSize() <= 0)
            throw new IllegalArgumentException("Assets size is not positive");
        if (configurationData.getNumOfRows() <= 0)
            throw new IllegalArgumentException("Number of rows is not positive");
        if (configurationData.getNumOfCols() <= 0)
            throw new IllegalArgumentException("Number of columns is not positive");
        if (configurationData.getPieceMovementSpeed() <= 0)
            throw new IllegalArgumentException("Piece movement speed is not positive");
        int numOfPawnsPlayers = 0;
        int numofTigerPlayers = 0;
        for (PlayerDetails playerDetails : configurationData.getPlayersDetails()) {
            if (playerDetails.getRole().equals("pawns"))
            {
                numOfPawnsPlayers++;
                continue;
            }
            if (playerDetails.getRole().equals("tiger"))
            {
                numofTigerPlayers++;
                continue;
            }

            throw new IllegalArgumentException("Player role is not valid");

        }
    }

    private String readJsonFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    @Override
    public void addPlayerDetails(PlayerDetails playerDetails) {
        configurationData.getPlayersDetails().add(playerDetails);
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
    public int GetPieceMovementSpeed() {
        return configurationData.getPieceMovementSpeed();
    }
}
