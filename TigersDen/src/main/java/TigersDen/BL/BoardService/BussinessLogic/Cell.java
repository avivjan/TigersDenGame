package TigersDen.BL.BoardService.BussinessLogic;



import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.BussinesLogic.HumanPlayer;

public class Cell implements ICell{
    private CellStatus status;
    private IPiece pieceOnIt;
    private ICoordinate coordinate;

    
    public Cell(CellStatus status, boolean selected, ICoordinate coordinate, IPiece pieceOnIt) {
        this.status = status;
        this.pieceOnIt = pieceOnIt;
        this.coordinate = coordinate;
    }

    @Override
    public CellStatus getStatus() {
        return status;
    }

    public boolean canBeSelected() 
    {
        return pieceOnIt != null && pieceOnIt.getOwningPlayer() instanceof HumanPlayer;
    }
    @Override
    public ICoordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setStatus(CellStatus cs) {
        this.status = cs;
    }


    @Override
    public IPiece getPieceOnIt() {
        return pieceOnIt;
    }

    @Override
    public void setPieceOnIt(IPiece piece) {
        this.pieceOnIt = piece;
    }
    @Override
    public boolean isEmpty() {
        return pieceOnIt == null;
    }
    
    
}
