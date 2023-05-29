package TigersDen.UI.DrawingService.DataModel;

import javax.swing.JButton;

import org.checkerframework.checker.units.qual.C;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import java.awt.Graphics;

public class CustomButton extends JButton {
    private ICell cell;
    private ITurnManager turnManager;
    

    public CustomButton(ICell cell, ITurnManager turnManager) {
        super();
        this.cell = cell;
        this.turnManager = turnManager;
    }
    public CustomButton(ITurnManager turnManager) {
        super();
        this.turnManager = turnManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //turnManager.getPlayerInTurn().play();
    }
    public void setCell(ICell cell2) {
    }
}
