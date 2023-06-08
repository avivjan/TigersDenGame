package TigersDen.BL.MovementService.Contract;

import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.MovementService.DataModel.MovingDetails;

public interface IMovementService {
    void move() throws Exception;
    void AddMovingDetails(MovingDetails movingDetails) throws Exception;
    boolean isMoving(); 
    boolean isMoving(IPiece piece); 
}
 