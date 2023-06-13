package TigersDen.BL.BoardService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.BussinessLogic.Pieces.TigerPiece;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IObjectCreator;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.BL.ConfigurationService.DataModel.PlayerDetails;
import TigersDen.BL.PlayerService.BussinesLogic.CpuPlayer;
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
        // run foreach on players from config
        for (PlayerDetails playerDetails : configService.getPlayersDetails()) {
            if (playerDetails.isHuman()) {
                turnManager.addPlayer(
                        new HumanPlayer(playerDetails.getName(), playerDetails.getColor(), playerDetails.getRole()));
            } else {
                turnManager.addPlayer(
                        new CpuPlayer(playerDetails.getName(), playerDetails.getColor(), playerDetails.getRole()));
            }
        }
    }

    @Override
    public void createPieces() throws Exception {
        try {
            IPiece piece = new TigerPiece(Coordinate.createSpacialInstance(), turnManager.getPlayerByRole("tiger"));
            board.addPiece(piece);

            for (int row = numOfRows - 2; row < numOfRows; row++) {
                for (int column = 0; column < numOfCols; column++) {
                    ICoordinate tempCor = Coordinate.createInstance(row, column, false);
                    IPiece tmpPiece = new PawnPiece(tempCor,
                            turnManager.getPlayerByRole("pawns"));
                    board.addPiece(tmpPiece);
                }
            }           
        } catch (Exception e) {
            System.err.println("Error in createPieces");
            e.printStackTrace();
            throw e;
        }
    }

}
