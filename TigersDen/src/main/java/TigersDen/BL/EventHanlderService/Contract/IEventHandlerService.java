package TigersDen.BL.EventHanlderService.Contract;

import TigersDen.BL.BoardService.Model.ICell;
import processing.event.MouseEvent;

public interface IEventHandlerService {
    void handleClick(MouseEvent event);
}
