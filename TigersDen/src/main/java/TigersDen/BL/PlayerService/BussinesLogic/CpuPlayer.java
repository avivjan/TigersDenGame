package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Model.ICell;

public class CpuPlayer extends AbstractPlayer{
    public CpuPlayer(String name, String color, String role) {
        super(name, color, role);
    }

    @Override
    public boolean play(ICell cellClicked) throws Exception {
        if (cellClicked != null)
        {
            throw new Exception("The play method of the CpuPlayer class should not be called with a cell.");
        }
        //AI!
        return true;
    }
}
