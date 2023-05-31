package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class TigerPiece extends AbstractPiece {

    public TigerPiece(ICoordinate coordinate2, IPlayer player) {
        super(coordinate2, player);
    }

    @Override
    public List<ICell> getOptionalMovements(IBoard board) throws Exception {
        return new PawnPiece(this.getCoordinate(), getOwningPlayer()).getOptionalMovements(board);
    }
}
