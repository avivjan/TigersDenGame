package TigersDen.BL.BoardService.BussinessLogic;

import java.awt.Color;

import javax.swing.JButton;

import org.checkerframework.checker.units.qual.C;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.spi.Dependency;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.EventHanlderService.Contract.IEventHandlerService;
import TigersDen.BL.TurnManager.BussinessLogic.TurnManager;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;
import TigersDen.DI.GuiceModule;
import TigersDen.UI.DrawingService.DataModel.CustomButton;

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
    public void createBoard() throws Exception {
        

        CustomButton lionTenbButton = new CustomButton(Guice.createInjector(new GuiceModule()).getInstance(IEventHandlerService.class));
        lionTenbButton.setBounds((numOfCols / 2) * cellSize , 0, cellSize, cellSize);
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

        ICoordinate cor =Coordinate.createSpacialInstance();
        board.setButtonOfCell(cor,  lionTenbButton);
        lionTenbButton.setCell(board.getTigerDenCell());
        
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                CustomButton button = new CustomButton(Guice.createInjector(new GuiceModule()).getInstance(IEventHandlerService.class));
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
                ICoordinate coordinate =Coordinate.createInstance(i, j, false);
                board.setButtonOfCell(coordinate, button);
                button.setCell(board.getCell(coordinate));
            }
        }

    }

    @Override
    public void createPieces() {
        try {
            IPiece piece = new PawnPiece(Coordinate.createSpacialInstance());
            board.addPiece(piece);

            for (int row = numOfRows - 2; row < numOfRows; row++) {
                for (int column = 0; column < numOfCols; column++) {
                    board.addPiece(new PawnPiece(Coordinate.createInstance(row, column, false)));
                }
            }
        } catch (Exception e) {
            System.err.println("Error in createPieces");
            e.printStackTrace();
        }
    }

    @Override
    public void createPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

}
