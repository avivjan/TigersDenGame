package TigersDen.BL.PlayerService.Contract;

import TigersDen.BL.BoardService.Model.ICell;

public interface IPlayer
{
    String getName();

    boolean play(ICell cellClicked) throws Exception;
    
    String getColor();

    String getRole();

    boolean isHuman();
}