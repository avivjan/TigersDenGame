package TigersDen.DAL.DataModel;

import java.util.UUID;

import TigersDen.DAL.Contract.IStorable;

public class BoardData implements IStorable {
    private UUID guid;
    private int a;

    public BoardData(int a) {
        this.guid = UUID.randomUUID();
    }
    
    @Override
    public UUID getId() {
        return guid;
    }
}
