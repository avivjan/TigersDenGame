package TigersDen.DI;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

import TigersDen.BL.BoardService.BussinessLogic.Board;
import TigersDen.BL.BoardService.BussinessLogic.Cell;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.BussinessLogic.BoardData;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.UI.DrawingService.BussinessLogic.DrawingService;
import TigersDen.UI.DrawingService.BussinessLogic.ObjectCreator;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.IObjectCreator;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind your classes/interfaces hereÍ

        bind(IDrawingService.class).to(DrawingService.class);
        
        bind(IBoard.class).to(Board.class);
        
        bind(IObjectCreator.class).to(ObjectCreator.class);
        bind(IBoardData.class).toInstance(new BoardData(9,9, 100));

        
    }
}
