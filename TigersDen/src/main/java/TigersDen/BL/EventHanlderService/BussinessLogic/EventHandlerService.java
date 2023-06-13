package TigersDen.BL.EventHanlderService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.ConfigurationService.DataModel.PlayerDetails;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.GameStateService.Contract.IGameStateService;
import TigersDen.BL.GameStateService.DataModel.GameState;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import processing.event.MouseEvent;

public class EventHandlerService implements IEventHandlerService {
    private ITurnManager turnManager;
    private IBoard board;
    private IGameStateService gameStateService;
    private IConfigurationService configurationService;
    private IObjectCreator objectCreator;

    private int button1X, button1Y, button2X, button2Y, button3X, button3Y;
    private int buttonWidth = 250;
    private int buttonHeight = 80;
    private int buttonSpacing = 30;

    @Inject
    public EventHandlerService(ITurnManager turnManager, IBoard board,
            IConfigurationService configurationService, IGameStateService gameStateService,
            IObjectCreator objectCreator) {
        super();
        this.turnManager = turnManager;
        this.board = board;
        this.gameStateService = gameStateService;
        this.configurationService = configurationService;
        int WIDTH = configurationService.getScreenWidth();
        int HEIGHT = configurationService.getScreenHeight();
        button1X = WIDTH / 2 - buttonWidth / 2;
        button1Y = HEIGHT / 2 - 180;
        button2X = button1X;
        button2Y = button1Y + buttonHeight + buttonSpacing;
        button3X = button1X;
        button3Y = button2Y + buttonHeight + buttonSpacing;
        this.objectCreator = objectCreator;
    }

    @Override
    public void handleClick(MouseEvent event) throws Exception {
        try {
            if (gameStateService.getGameState() == GameState.MAIN_MENU) {
                HandleClickOnMainManuScreen(event);
                return;
            } else if (gameStateService.getGameState() == GameState.IN_GAME_AI_As_Pawns ||
                       gameStateService.getGameState() == GameState.IN_GAME_AI_As_Tiger ||
                        gameStateService.getGameState() == GameState.IN_GAME_PVP) {
                HandleClickOnGameScreen(event);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void HandleClickOnGameScreen(MouseEvent event) throws Exception {
        IPlayer getPlayerInTurn = turnManager.getPlayerInTurn();
        if (getPlayerInTurn.isHuman() == false) {
            System.out.println("Please wait for the CPU to finish his turn.");
            return;
        }
        ICoordinate cor = Coordinate.createInstance(event.getX(), event.getY(), true);
        ICell cellClicked = board.getCell(cor);
        if (cellClicked == null) {
            System.err.println("The cell you pressed is not on the board!");
            return;
        }
        getPlayerInTurn.play(cellClicked);
    }

    private void HandleClickOnMainManuScreen(MouseEvent e) throws Exception {
        // Check if the mouse click is within the bounds of Button 1
        if (e.getX() >= button1X && e.getX() <= button1X + buttonWidth &&
                e.getY() >= button1Y && e.getY() <= button1Y + buttonHeight) {
            // Button 1 clicked
            handleButton1Click();
        }

        // Check if the mouse click is within the bounds of Button 2
        if (e.getX() >= button2X && e.getX() <= button2X + buttonWidth &&
                e.getY() >= button2Y && e.getY() <= button2Y + buttonHeight) {
            // Button 2 clicked
            handleButton2Click();
        }

        // Check if the mouse click is within the bounds of Button 3
        if (e.getX() >= button3X && e.getX() <= button3X + buttonWidth &&
                e.getY() >= button3Y && e.getY() <= button3Y + buttonHeight) {
            // Button 3 clicked
            handleButton3Click();
        }

    }

    private void handleButton3Click() throws Exception {
        configurationService.addPlayerDetails(new PlayerDetails("Player 1", "white", "pawns", true));
        configurationService.addPlayerDetails(new PlayerDetails("Player 2 AI", "black", "tiger", false));
        objectCreator.createPlayers();
        objectCreator.createPieces();
        gameStateService.setGameState(GameState.IN_GAME_AI_As_Pawns);
    }

    private void handleButton2Click() throws Exception {
        configurationService.addPlayerDetails(new PlayerDetails("Player 1", "white", "tiger", true));
        configurationService.addPlayerDetails(new PlayerDetails("Player 2 AI", "black", "pawns", false));
        objectCreator.createPlayers();
        objectCreator.createPieces();
        gameStateService.setGameState(GameState.IN_GAME_AI_As_Tiger);

    }

    private void handleButton1Click() throws Exception {
        System.out.println("Button 1 clicked");
        configurationService.addPlayerDetails(new PlayerDetails("Player 1", "white", "tiger", true));
        configurationService.addPlayerDetails(new PlayerDetails("Player 2", "black", "pawns", true));
        objectCreator.createPlayers();
        objectCreator.createPieces();
        gameStateService.setGameState(GameState.IN_GAME_PVP);
    }
}
