package TigersDen.DI;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import TigersDen.BL.BoardService.BussinessLogic.Board;
import TigersDen.BL.BoardService.BussinessLogic.ObjectCreator;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardValidator.BussinessLogic.BoardValidator;
import TigersDen.BL.BoardValidator.Contract.IBoardValidator;
import TigersDen.BL.ConfigurationService.BussinessLogic.JsonConfigurationService;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.EventHanlderService.BussinessLogic.EventHandlerService;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.MovementService.BussinessLogic.MovementService;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.TurnManager.BussinessLogic.InMemoryTurnManager;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import TigersDen.DAL.BussinessLogic.BoardData;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.UI.DrawingService.BussinessLogic.DrawingService;
import TigersDen.UI.DrawingService.BussinessLogic.PAppletWrapper;
import TigersDen.UI.DrawingService.BussinessLogic.SpriteManager;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.IPAppletWrapper;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;
import processing.core.PApplet;

public class GuiceModule extends AbstractModule {
    private final PApplet applet;

    public GuiceModule(PApplet applet) {
        this.applet = applet;
    }
    
    @Override
    protected void configure() {
        // Bind your classes/interfaces hereÍ

        bind(IDrawingService.class).to(DrawingService.class).in(Singleton.class);
        bind(IBoard.class).to(Board.class).in(Singleton.class);
        bind(IObjectCreator.class).to(ObjectCreator.class).in(Singleton.class);
        bind(ISpriteManager.class).to(SpriteManager.class).in(Singleton.class);
        bind(ITurnManager.class).to(InMemoryTurnManager.class).in(Singleton.class);
        bind(IEventHandlerService.class).to(EventHandlerService.class).in(Singleton.class);
        bind(PApplet.class).toInstance(applet);
        bind(IPAppletWrapper.class).to(PAppletWrapper.class);
        bind(IConfigurationService.class).to(JsonConfigurationService.class).in(Singleton.class);
        bind(IMovementService.class).to(MovementService.class).in(Singleton.class);
        bind(IBoardData.class).to(BoardData.class).in(Singleton.class);
        bind(IBoardValidator.class).to(BoardValidator.class).in(Singleton.class);
        
    }
    
}
