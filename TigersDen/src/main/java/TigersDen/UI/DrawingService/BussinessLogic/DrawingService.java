package TigersDen.UI.DrawingService.BussinessLogic;

import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import org.checkerframework.checker.units.qual.C;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.DAL.BussinessLogic.BoardData;
import TigersDen.DAL.Contract.IBoardData;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class DrawingService implements IDrawingService {
    private final int SPRITESIZE = 480;// TODO: move to config/DAL
    public int cellSize = 100;// TODO: move to config/DAL
    public int numOfRows = 9;// TODO: move to config/DAL
    public int numOfCols = 9;// TODO: move to config/DAL
    public int windowLabel = 30;

    public int WIDTH = cellSize * numOfCols;
    public int HEIGHT = cellSize * (numOfRows + 1) + windowLabel;

    private JFrame jFrame;
    private JPanel jPanel;
    private IBoard board;



    @Inject
    public DrawingService(IBoard board) {
        this.jFrame = new JFrame();
        this.jPanel = new JPanel();
        jPanel.setLayout(null);

        this.board = board;
    }


    @Override
    public void drawBoard() throws Exception {
        jPanel.add(board.getTigerDenCell().getbutton());
        //draw all board buttons
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                ICell cell = board.getCell((ICoordinate)Coordinate.createInstance(j, i, false, false));
                jPanel.add(cell.getbutton());
            }
        }
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    @Override
    public void createFrame() throws Exception {
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setTitle("Tiger's Den");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
