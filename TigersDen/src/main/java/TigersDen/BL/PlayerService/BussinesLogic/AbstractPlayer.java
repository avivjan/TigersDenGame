package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.MovementService.BussinessLogic.MovementService;
import TigersDen.BL.MovementService.Contract.IMovementService;
import TigersDen.BL.PlayerService.Contract.IPlayer;
import TigersDen.DI.InjectorStorage;

public abstract class AbstractPlayer implements IPlayer {
    private String name;
    private String color;
    private boolean isHuman;
    private String role;
    protected IMovementService movementService;

    public AbstractPlayer(String name, String color, String role) {
        this.name = name;
        this.color = color;
        this.role = role;
        movementService = InjectorStorage.getInjector().getInstance(IMovementService.class);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public boolean isHuman() {
        return isHuman;
    }

}
