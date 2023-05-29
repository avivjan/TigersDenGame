package TigersDen.UI.DrawingService.BussinessLogic;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;

public class DrawingService implements IDrawingService {
    private final int SPRITESIZE = 100;// TODO: move to config/DAL
    public int cellSize = 100;// TODO: move to config/DAL
    public int numOfRows = 9;// TODO: move to config/DAL
    public int numOfCols = 9;// TODO: move to config/DAL
    public int windowLabel = 30;

    public int WIDTH = cellSize * numOfCols;
    public int HEIGHT = cellSize * (numOfRows + 1) + windowLabel;

    private JFrame jFrame;
    private JLayeredPane layeredPane;
    private IBoard board;
    private ISpriteManager spriteManager;

    @Inject
    public DrawingService(IBoard board, ISpriteManager spriteManager) {
        this.jFrame = new JFrame();
        this.layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        this.board = board;
        this.spriteManager = spriteManager;
    }

    @Override
    public void drawBoard() throws Exception {
        layeredPane.add(board.getTigerDenCell().getbutton());
        // draw all board buttons
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                ICell cell = board.getCell((ICoordinate) Coordinate.createInstance(j, i, false));
                layeredPane.add(cell.getbutton(), new Integer(1));
            }
        }
        jFrame.add(layeredPane);
        jFrame.setVisible(true);
    }

    @Override
    public void createFrame() throws Exception {
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setTitle("Tiger's Den");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    @Override
    public void drawPieces() throws Exception {
        for (IPiece piece : board.getPieces()) {
            ICoordinate coordinate = piece.getCoordinate();
            ImageIcon sprite = spriteManager.getSprite(piece);

            JLabel label = new JLabel(sprite);
            label.setBounds(coordinate.getColumn() * cellSize, (coordinate.getRow() + 1) * cellSize, SPRITESIZE,SPRITESIZE);
            layeredPane.add(label, new Integer(2));
            jFrame.add(layeredPane);
            jFrame.setVisible(true);
        }
    }
}
