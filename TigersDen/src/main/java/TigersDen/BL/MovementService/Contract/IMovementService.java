package TigersDen.BL.MovementService.Contract;

import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;

public interface IMovementService {
    void move() throws Exception;
    boolean isMoving(); 
    boolean isMoving(IPiece piece);
    void ApplyMove(IPiece pieceToMove,ICell moveFrom, ICell moveTo) throws Exception; 
}
 