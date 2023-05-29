package TigersDen.DI;

import com.google.inject.AbstractModule;
import TigersDen.BL.BoardService.BussinessLogic.Board;
import TigersDen.BL.BoardService.BussinessLogic.ObjectCreator;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
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
        bind(IBoardData.class).toInstance(new BoardData(9,9, 100));//TODO: move to config/DAL?
        bind(ISpriteManager.class).to(SpriteManager.class);

        
    }
}
