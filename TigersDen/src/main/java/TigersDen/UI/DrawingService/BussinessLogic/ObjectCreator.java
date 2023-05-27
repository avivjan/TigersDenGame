package TigersDen.UI.DrawingService.BussinessLogic;

import java.awt.Color;

import javax.swing.JButton;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Cell;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.UI.DrawingService.Contract.IObjectCreator;

public class ObjectCreator implements IObjectCreator {
    private final int SPRITESIZE = 480;// TODO: move to config/DAL
    public int cellSize = 100;// TODO: move to config/DAL
    public int numOfRows = 9;// TODO: move to config/DAL
    public int numOfCols = 9;// TODO: move to config/DAL
    public int windowLabel = 30;

    public int WIDTH = cellSize * numOfCols;
    public int HEIGHT = cellSize * (numOfRows + 1) + windowLabel;

    private IBoard board;

    @Inject
    public ObjectCreator(IBoard board) {
        this.board = board;
    }

    @Override
    public void createBoard() {
        
        JButton lionTenbButton = new JButton();
        lionTenbButton.setBounds(numOfCols * cellSize / 2 - (cellSize / 2), 0, cellSize, cellSize);
        lionTenbButton.setOpaque(true);
        lionTenbButton.setBackground(Color.orange);
        lionTenbButton.setBorderPainted(true);
        lionTenbButton.setName("Tiger's Den");
        lionTenbButton.addActionListener(e -> {
            try {
                System.out.println("Button clicked: " + lionTenbButton.getName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        ICoordinate cor =Coordinate.createInstance(0,0, false, true);
        board.setButtonOfCell(cor,  lionTenbButton);
        
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                JButton button = new JButton();
                button.setBounds(j * cellSize, (i + 1) * cellSize, cellSize, cellSize);
                button.setOpaque(true);
                button.setBackground(Color.orange);
                button.setBorderPainted(true);
                button.setName(i + "," + j);
                button.addActionListener(e -> {
                    try {
                        System.out.println("Button clicked: " + ((JButton)e.getSource()).getName());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                ICoordinate coordinate =Coordinate.createInstance(i, j, false, false);
                board.setButtonOfCell(coordinate, button);
            }
        }

    }

    @Override
    public void createPieces() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public void createPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

}
