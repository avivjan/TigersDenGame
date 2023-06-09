package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.AI.Contract.ITigerDenBrain;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.MovementService.DataModel.MovingDetails;
import TigersDen.DI.InjectorStorage;

public class CpuPlayer extends AbstractPlayer{
    private ITigerDenBrain brain;

    public CpuPlayer(String name, String color, String role) {
        super(name, color, role);
        this.brain = InjectorStorage.getInjector().getInstance(ITigerDenBrain.class);
    }

    @Override
    public boolean play(ICell cellClicked) throws Exception {
        if (cellClicked != null)
        {
            throw new Exception("The play method of the CpuPlayer class should not be called with a cell.");
        }
        //movementService.ApplyMove(brain.minimax(0, true));
        return true;//The turn is over
    }
}
