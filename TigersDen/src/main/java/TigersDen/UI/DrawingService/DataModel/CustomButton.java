package TigersDen.UI.DrawingService.DataModel;

import java.awt.Graphics;

import javax.swing.JButton;

import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;

public class CustomButton extends JButton {
    private ICell cell;
    private IEventHandlerService eventHandlerService;

    public CustomButton(IEventHandlerService eventHandlerService1) {
        super();
        this.eventHandlerService = eventHandlerService1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getModel().isPressed()) {

            eventHandlerService.handleClick(cell);
        }
    }

    public void setCell(ICell cell) {
        this.cell = cell;
    }
}
