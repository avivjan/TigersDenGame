package TigersDen.BL.PlayerService.BussinesLogic;

import TigersDen.BL.PlayerService.Contract.IPlayer;

public abstract class AbstractPlayer implements IPlayer {
    private String name;
    private String color;

    public AbstractPlayer(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getColor() {
        return this.color;
    }
    
}
