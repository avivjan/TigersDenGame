package TigersDen.BL.BoardService.BussinessLogic.Pieces;

import java.util.List;

import TigersDen.BL.BoardService.BussinessLogic.AbstractPiece;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Model.ICell;

public class PawnPiece extends AbstractPiece {

    public PawnPiece(ICoordinate coordinate2) {
        super(coordinate2);   
    }

    @Override
    public List<ICell> getOptionalMovements() throws Exception {
        return null;
    }
}
