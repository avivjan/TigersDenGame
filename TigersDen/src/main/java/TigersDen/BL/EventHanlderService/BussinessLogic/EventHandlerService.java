package TigersDen.BL.EventHanlderService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class EventHandlerService implements IEventHandlerService {
    private ITurnManager turnManager;

    @Inject
    public EventHandlerService(ITurnManager turnManager) {
        super();
        this.turnManager = turnManager;
    }

    @Override
    public void handleClick(ICell cellClicked) {
        try {
            turnManager.getPlayerInTurn().play(cellClicked);
            turnManager.setNextPlayerInTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
