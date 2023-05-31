package TigersDen.BL.BoardService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class ObjectCreator implements IObjectCreator {
    public int cellSize = 100;// TODO: move to config/DAL
    public int numOfRows = 9;// TODO: move to config/DAL
    public int numOfCols = 9;// TODO: move to config/DAL

    public int WIDTH = cellSize * numOfCols;
    public int HEIGHT = cellSize * (numOfRows + 1);

    private IBoard board;
    private ITurnManager turnManager;

    @Inject
    public ObjectCreator(IBoard board, ITurnManager turnManager) {
        this.board = board;
        this.turnManager = turnManager;
    }

    @Override
    public void createPlayers() {
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
    
        turnManager.addPlayer(new HumanPlayer("Player1", "white"));
    }
    @Override
    public void createBoard() {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
                board.addCell(new Cell(CellStatus.None, false, Coordinate.createInstance(row, col, false), null));
            }
        }
        board.addCell(new Cell(CellStatus.None, false, Coordinate.createSpacialInstance(), null));
    }



    @Override
    public void createPieces() {
        try {
            IPiece piece = new PawnPiece(Coordinate.createSpacialInstance(), new HumanPlayer("Player1", "white"));//TODO:change player
            board.addPiece(piece);

            for (int row = numOfRows - 2; row < numOfRows; row++) {
                for (int column = 0; column < numOfCols; column++) {
                    ICoordinate tempCor = Coordinate.createInstance(row, column, false);
                    piece = new PawnPiece(tempCor, new HumanPlayer("Player1", "white"));//Change Player!
                    board.addPiece(piece);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in createPieces");
            e.printStackTrace();
        }
    }

}
