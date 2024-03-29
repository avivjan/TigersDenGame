package TigersDen;

import com.google.inject.Guice;
import com.google.inject.Injector;

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
            this.clickEventHandler = injector.getInstance(IEventHandlerService.class);
            this.movementService = injector.getInstance(IMovementService.class);
            this.boardData = injector.getInstance(IBoardData.class);
            
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
                movementService.move();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            this.exit();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        try {
            clickEventHandler.handleClick(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            this.exit();
        }
    }

    public static void main(String[] args) {
        PApplet.main("TigersDen.App");
    }
}
