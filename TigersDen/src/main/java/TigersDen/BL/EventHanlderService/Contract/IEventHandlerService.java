package TigersDen.BL.EventHanlderService.Contract;

import TigersDen.BL.BoardService.Model.ICell;

public interface IEventHandlerService {
    void handleClick(ICell cellClicked);
}
