package TigersDen.BL.EventHanlderService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import TigersDen.DI.InjectorStorage;
import processing.event.MouseEvent;

public class EventHandlerService implements IEventHandlerService {
    private ITurnManager turnManager;
    private IBoard board;

    @Inject
    public EventHandlerService(ITurnManager turnManager, IBoard board) {
        super();
        this.turnManager = turnManager;
        this.board = board;
    }

    @Override
    public void handleClick(MouseEvent event) {
        try {
            IBoard board = InjectorStorage.getInjector().getInstance(IBoard.class);
            ICoordinate cor = Coordinate.createInstance(event.getX(), event.getY(), true);
            System.out.println("(" + event.getX()+ ", " + event.getY() + ")");
            System.out.println(cor);
            ICell cellClicked = board.getCell(cor);
            turnManager.getPlayerInTurn().play(cellClicked);
            turnManager.setNextPlayerInTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
