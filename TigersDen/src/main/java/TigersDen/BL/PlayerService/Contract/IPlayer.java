package TigersDen.BL.PlayerService.Contract;

import TigersDen.BL.BoardService.Model.ICell;

public interface IPlayer
{
    String getName();

    void play(ICell cellClicked) throws Exception;
    
    String getColor();

    String getRole();

    boolean isHuman();

    IPlayer clone();
}