package TigersDen.UI.DrawingService.BussinessLogic;

import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.GameStateService.Contract.IGameStateService;
import TigersDen.BL.GameStateService.DataModel.GameState;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.IPAppletWrapper;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingService implements IDrawingService {
    private IPAppletWrapper pAppletWrapper;
    private IBoard board;
    private ISpriteManager spriteManager;
    private IConfigurationService configurationService;
    private IGameStateService gameStateService;
    private int button1X, button1Y, button2X, button2Y, button3X, button3Y;
    private int buttonWidth = 250;
    private int buttonHeight = 80;
    private int buttonSpacing = 30;
    private int WIDTH;
    private int HEIGHT;

    @Inject
    public DrawingService(IPAppletWrapper appletWrapper, IBoard board,
            ISpriteManager spriteManager, IConfigurationService configurationService,
            IGameStateService gameStateService) {
        this.pAppletWrapper = appletWrapper;
        this.board = board;
        this.spriteManager = spriteManager;
        this.configurationService = configurationService;
        this.gameStateService = gameStateService;
        WIDTH = configurationService.getScreenHeight();
        HEIGHT = configurationService.getScreenHeight();
        button1X = WIDTH / 2 - buttonWidth / 2 ;
        button1Y = HEIGHT / 2 - 180;
        button2X = button1X;
        button2Y = button1Y + buttonHeight + buttonSpacing;
        button3X = button1X;
        button3Y = button2Y + buttonHeight + buttonSpacing;
    }

    public void draw() throws Exception {
        GameState gameState = gameStateService.getGameState();
        switch (gameState) {
            case MAIN_MENU:
                drawMainMenuScreen();
                break;
            case LOSS_SCREEN:
                drawScreenWithMessage("You Lost!");
                break;
            case WIN_SCREEN:
                drawScreenWithMessage("You Won!");
                break;
            case TIGER_WIN_SCREEN:
                drawScreenWithMessage("Tigers Won!");
                break;
            case PAWNS_WIN_SCREEN:
                drawScreenWithMessage("Pawns Won!");
                break;
            case IN_GAME_AI_As_Pawns:
                drawGameScreen();
                break;
            case IN_GAME_AI_As_Tiger:
                drawGameScreen();
                break;
            case IN_GAME_PVP:
                drawGameScreen();
                break;
            default:
                break;
        }
    }

    private void drawScreenWithMessage(String message) {
        pAppletWrapper.background(200);
        pAppletWrapper.fill(250);
        pAppletWrapper.textSize(50);
        pAppletWrapper.textAlign(PApplet.CENTER, PApplet.CENTER);
        pAppletWrapper.text(message, WIDTH / 2, HEIGHT / 2);
    }

    private void drawMainMenuScreen() {
        // Draw main menu screen
        pAppletWrapper.background(200);
        pAppletWrapper.fill(250);
        pAppletWrapper.textSize(50);
        pAppletWrapper.textAlign(PApplet.CENTER, PApplet.CENTER);
        pAppletWrapper.text("Main Menu", WIDTH / 2, HEIGHT / 2 - 400); // Adjusted y-position

        // Draw Button 1
        pAppletWrapper.fill(255);
        pAppletWrapper.rect(button1X, button1Y, buttonWidth, buttonHeight);
        pAppletWrapper.fill(0);
        pAppletWrapper.textSize(24);
        pAppletWrapper.textAlign(PApplet.CENTER, PApplet.CENTER);
        pAppletWrapper.text("P vs P", button1X + buttonWidth / 2, button1Y + buttonHeight / 2);

        // Draw Button 2
        pAppletWrapper.fill(255);
        pAppletWrapper.rect(button2X, button2Y, buttonWidth, buttonHeight);
        pAppletWrapper.fill(0);
        pAppletWrapper.textSize(24);
        pAppletWrapper.textAlign(PApplet.CENTER, PApplet.CENTER);
        pAppletWrapper.text("U vs AI (as tiger)", button2X + buttonWidth / 2, button2Y + buttonHeight / 2);

        // Draw Button 3
        pAppletWrapper.fill(255);
        pAppletWrapper.rect(button3X, button3Y, buttonWidth, buttonHeight);
        pAppletWrapper.fill(0);
        pAppletWrapper.textSize(24);
        pAppletWrapper.textAlign(PApplet.CENTER, PApplet.CENTER);
        pAppletWrapper.text("U vs AI (as pawns)", button3X + buttonWidth / 2, button3Y + buttonHeight / 2);
    }

    private void drawGameScreen() throws Exception {
        pAppletWrapper.background(200);
        pAppletWrapper.fill(250);
        drawCells();
        drawPieces();
    }

    private void drawPieces() {
        List<IPiece> pieces = board.getPieces();
        for (IPiece piece : pieces) {
            if (piece.isCaptured()) {
                continue;
            }
            PImage sprite = spriteManager.getSprite(piece, piece.getOwningPlayer().getColor());
            if (sprite == null) {
                System.out.println("Error: Sprite is null for piece: " + piece.getClass().getSimpleName());
            }
            ICoordinate coordinate = piece.getCoordinate();

            int pixelX = coordinate.getXInPixels();
            int pixelY = coordinate.getYInPixels();
            // Draw the image
            pAppletWrapper.image(sprite, pixelX, pixelY, configurationService.getAssetsSize(),
                    configurationService.getAssetsSize());// Get From config
        }
    }

    private void drawCells() throws Exception {
        for (ICell cell : board.getCells()) {
            int cellSize = configurationService.getCellSize();

            decideAColor(cell.getCoordinate());
            pAppletWrapper.rect(cell.getCoordinate().getXInPixels(),
                    cell.getCoordinate().getYInPixels(),
                    cellSize,
                    cellSize);
        }
    }

    private void decideAColor(ICoordinate coordinate) throws Exception {
        ICell cell = board.getCell(coordinate);
        CellStatus cellStatus = cell.getStatus();
        switch (cellStatus) {
            case Option:
                pAppletWrapper.fill(0, 0, 255); // Blue
                break;
            case OptionWithCapture:
                pAppletWrapper.fill(255, 0, 0); // Red
                break;
            case Selected:
                pAppletWrapper.fill(0, 255, 0); // Green
                break;
            case None:
                if ((coordinate.getRow() + coordinate.getColumn()) % 2 == 0) {
                    pAppletWrapper.fill(255, 235, 205);
                } else {
                    pAppletWrapper.fill(200, 150, 50);
                }
                break;
            default:
                throw new Exception("Invalid cell status");
        }
    }
}
