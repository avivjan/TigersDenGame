package TigersDen.DI;

import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import TigersDen.BL.BoardService.BussinessLogic.Board;
import TigersDen.BL.BoardService.BussinessLogic.ObjectCreator;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.EventHanlderService.BussinessLogic.EventHandlerService;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.BL.TurnManager.BussinessLogic.TurnManager;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import TigersDen.DAL.BussinessLogic.BoardData;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.UI.DrawingService.BussinessLogic.DrawingService;
import TigersDen.UI.DrawingService.BussinessLogic.SpriteManager;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind your classes/interfaces hereÍ

        bind(IDrawingService.class).to(DrawingService.class);
        bind(IBoard.class).to(Board.class);
        bind(IObjectCreator.class).to(ObjectCreator.class);
        bind(ISpriteManager.class).to(SpriteManager.class);
        bind(ITurnManager.class).to(TurnManager.class); 
        bind(IEventHandlerService.class).to(EventHandlerService.class);

        bind(IBoardData.class).toInstance(new BoardData(9,9, 100));//TODO: move to config/DAL?
        

    }
    
}
