package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.PlayerService.Contract.IPlayer;

public abstract class AbstractPlayer implements IPlayer {
    private String name;
    private String color;
    private boolean isHuman;
    private String role;

    public AbstractPlayer(String name, String color, String role) {
        this.name = name;
        this.color = color;
        this.role = role;
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
