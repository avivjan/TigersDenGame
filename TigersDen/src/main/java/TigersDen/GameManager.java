package TigersDen;

import com.google.inject.Guice;
import com.google.inject.Injector;

import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.DI.GuiceModule;
import TigersDen.UI.DrawingService.Contract.IDrawingService;

public class GameManager {
    private IDrawingService drawingService;
    private IObjectCreator objectCreator;
    private IBoardData boardData;

    public void start() {
        try
        {
            Injector injector = Guice.createInjector(new GuiceModule());
            IDrawingService drawingService = injector.getInstance(IDrawingService.class);
            IObjectCreator objectCreator = injector.getInstance(IObjectCreator.class);
            IBoardData boardData = injector.getInstance(IBoardData.class);
            objectCreator.createBoard();
            objectCreator.createPieces();
            //objectCreator.createPlayers();
            drawingService.createFrame();
            drawingService.drawBoard();
            drawingService.drawPieces();

            int a = 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    

}
