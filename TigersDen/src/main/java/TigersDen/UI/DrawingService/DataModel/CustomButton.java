package TigersDen.UI.DrawingService.DataModel;

import java.awt.Color;
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
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // if (getModel().isArmed()) {
        //     eventHandlerService.handleClick(cell);
        // }
        // btn.getModel()
        // });
    }

    public void setCell(ICell cell) {
        this.cell = cell;
    }

    
}
