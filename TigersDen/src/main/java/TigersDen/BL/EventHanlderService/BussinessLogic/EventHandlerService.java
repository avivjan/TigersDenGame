package TigersDen.BL.EventHanlderService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import processing.event.MouseEvent;

public class EventHandlerService implements IEventHandlerService {
    private ITurnManager turnManager;
    private IBoard board;
    @Inject
    public EventHandlerService(ITurnManager turnManager, IBoard board,
            IConfigurationService configurationService) {
        super();
        this.turnManager = turnManager;
        this.board = board;
    }

    @Override
    public void handleClick(MouseEvent event) {
        try {
            System.out.println("Mouse Clicked" + "(x: " + event.getX() + ", " + event.getY() + ")");
            ICoordinate cor = Coordinate.createInstance(event.getX(), event.getY(), true);
            System.out.println("(" + event.getX() + ", " + event.getY() + ")");
            System.out.println(cor);
            ICell cellClicked = board.getCell(cor);
            if (cellClicked == null) {
                System.err.println("The cell you pressed is not on the board!");
                return;
            }
            boolean isTurnOver = turnManager.getPlayerInTurn().play(cellClicked);
            if (isTurnOver)
            {
                turnManager.setNextPlayerInTurn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
