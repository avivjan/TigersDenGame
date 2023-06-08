package TigersDen.BL.BoardService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
// import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;
import TigersDen.BL.TurnManager.Contracts.ITurnManager;

public class ObjectCreator implements IObjectCreator {
    private int numOfRows;
    private int numOfCols;

    private IBoard board;
    private ITurnManager turnManager;
    private IConfigurationService configService;

    @Inject
    public ObjectCreator(IBoard board, ITurnManager turnManager, IConfigurationService configService) {
        this.board = board;
        this.turnManager = turnManager;
        this.configService = configService;
        this.numOfRows = configService.getNumOfRows();
        this.numOfCols = configService.getNumOfCols();
    }

    @Override
    public void createPlayers() {
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
        // TODO  a config file, creates players and add them to turn manager !!!!!!!!
    
        turnManager.addPlayer(new HumanPlayer("Player1", "white"));
    }



    @Override
    public void createPieces() {
        try {
            IPiece piece = new PawnPiece(Coordinate.createSpacialInstance(), new HumanPlayer("Player1", "white"));//TODO:change player
            board.addPiece(piece);

            for (int row = numOfRows - 2; row < numOfRows; row++) {
                for (int column = 0; column < numOfCols; column++) {
                    ICoordinate tempCor = Coordinate.createInstance(row, column, false);
                    IPiece tmpPiece = new PawnPiece(tempCor, new HumanPlayer("Player1", "white"));//Change Player!
                    board.addPiece(tmpPiece);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in createPieces");
            e.printStackTrace();
        }
    }

}
