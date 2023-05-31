package TigersDen;

import com.google.inject.Guice;
import com.google.inject.Injector;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.DI.GuiceModule;
import TigersDen.DI.InjectorStorage;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class App extends PApplet {
    public String configPath;
    public static final int SPRITESIZE = 100;// TODO:config
    public static final int CELLSIZE = 100;// TODO:config
    public static final int BOARD_WIDTH = 9;// TODO:config
    public static final int BOARD_HEIGHT = 10;// TODO:config

    public static int WIDTH = CELLSIZE * BOARD_WIDTH;
    public static int HEIGHT = CELLSIZE * BOARD_HEIGHT;

    public static final int FPS = 60;

    private IDrawingService drawingService;
    private IObjectCreator objectCreator;
    private IEventHandlerService clickEventHandler;

    public App() {
        this.configPath = "config.json";
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {

        frameRate(FPS);
        Injector injector = Guice.createInjector(new GuiceModule(this));
        InjectorStorage.setInjector(injector);
        this.drawingService = injector.getInstance(IDrawingService.class);
        this.objectCreator = injector.getInstance(IObjectCreator.class);
        this.clickEventHandler = injector.getInstance(IEventHandlerService.class);
        IBoardData bd = injector.getInstance(IBoardData.class);

        objectCreator.createBoard();
        objectCreator.createPieces();
        objectCreator.createPlayers();
    }

    /**
     * Draw all elements in the game by current frame.
     */
    public void draw() {
        try {
            drawingService.draw();
            // if(movingService.isMoving())
            // {
            // movingService.move();
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickEventHandler.handleClick(e);
    }

    public static void main(String[] args) {
        PApplet.main("TigersDen.App");
    }
}
