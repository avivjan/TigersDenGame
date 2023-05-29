package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.PlayerService.Contract.IPlayer;

public class HumanPlayer extends AbstractPlayer{
    public HumanPlayer(String name, String color) {
        super(name, color);
    }

    @Override
    public void play(ICell cellClicked) {
        // TODO Auto-generated method stub
    }
}
