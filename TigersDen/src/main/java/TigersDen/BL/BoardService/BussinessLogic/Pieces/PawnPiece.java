package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class PawnPiece extends AbstractPiece {

    public PawnPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player);   
    }

    @Override
    public List<ICell> getOptionalMovements() throws Exception {
        return null;
    }
}
