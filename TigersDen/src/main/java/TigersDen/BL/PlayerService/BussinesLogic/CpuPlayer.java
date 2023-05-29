package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class CpuPlayer extends AbstractPlayer{
    public CpuPlayer(String name, String color) {
        super(name, color);
    }

    @Override
    public void play(ICell cellClicked) throws Exception {
        if (cellClicked != null)
        {
            throw new Exception("The play method of the CpuPlayer class should not be called with a cell.");
        }
        //AI!
    }
}
