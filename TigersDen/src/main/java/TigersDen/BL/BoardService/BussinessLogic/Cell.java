package TigersDen.BL.BoardService.BussinessLogic;

import java.awt.Component;

import javax.swing.JButton;

import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;

public class Cell implements ICell{
    private JButton button;
    private CellStatus status;
    private boolean selected;
    //private IPiece pieceOnIt;
    private ICoordinate coordinate;

    
    public Cell(CellStatus status, boolean selected, ICoordinate coordinate, JButton button) {
        this.status = status;
        this.selected = selected;
        //this.pieceOnIt = pieceOnIt;
        this.coordinate = coordinate;
        this.button = button;
    }

    @Override
    public CellStatus getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

    @Override
    public boolean isSelected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSelected'");
    }

    @Override
    public boolean canBeSelected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBeSelected'");
    }

    @Override
    public ICoordinate getCoordinate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoordinate'");
    }

    @Override
    public void setStatus(CellStatus cs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

    @Override
    public void setButtonOfCell(JButton button) {
        this.button = button;
    }

    @Override
    public Component getbutton() {
        return this.button;
    }
    
    
}
