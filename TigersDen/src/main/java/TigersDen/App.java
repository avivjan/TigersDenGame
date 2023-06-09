package TigersDen;

import com.google.inject.Guice;
import com.google.inject.Injector;

import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.DI.GuiceModule;
import TigersDen.DI.InjectorStorage;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class App extends PApplet {
    public static int WIDTH;
    public static int HEIGHT;

    public static final int FPS = 60;

    private IDrawingService drawingService;
    private IObjectCreator objectCreator;
    private IEventHandlerService clickEventHandler;
    private IMovementService movementService;
    IBoardData boardData;

    public App() {
    }

    public void settings() {
        Injector injector = Guice.createInjector(new GuiceModule(this));
        IConfigurationService configurationService = injector.getInstance(IConfigurationService.class);
        WIDTH = configurationService.getScreenWidth();
        HEIGHT = configurationService.getScreenHeight();
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        try {
            frameRate(FPS);
            Injector injector = Guice.createInjector(new GuiceModule(this));
            InjectorStorage.setInjector(injector);
            this.drawingService = injector.getInstance(IDrawingService.class);
            this.objectCreator = injector.getInstance(IObjectCreator.class);
            this.clickEventHandler = injector.getInstance(IEventHandlerService.class);
            this.movementService = injector.getInstance(IMovementService.class);
            this.boardData = injector.getInstance(IBoardData.class);
           
            

            objectCreator.createPlayers();
            objectCreator.createPieces();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw all elements in the game by current frame.
     */
    public void draw() {
        try {
            drawingService.draw();
            
            if(movementService.isMoving())
            {
                System.out.println("is moving");
                movementService.move();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            this.exit();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        clickEventHandler.handleClick(e);
    }

    public static void main(String[] args) {
        PApplet.main("TigersDen.App");
    }
}
