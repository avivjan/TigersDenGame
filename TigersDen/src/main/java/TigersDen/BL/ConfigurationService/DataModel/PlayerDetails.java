package TigersDen.BL.ConfigurationService.DataModel;

public class PlayerDetails {
    private String name;
    private String color;
    private boolean isHuman;
    private String role;

    public PlayerDetails(String name, String color, String role, boolean isHuman) {
        this.name = name;
        this.color = color;
        this.role = role;
        this.isHuman = isHuman;
    }

    
    public String getName() {
        return this.name;
    }


    public String getColor() {
        return this.color;
    }

   
    public String getRole() {
        return role;
    }

    
    public boolean isHuman() {
        return isHuman;
    }
    
}
