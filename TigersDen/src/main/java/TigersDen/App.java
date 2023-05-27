package TigersDen;



public class App {	
    public String configPath;

    public App() {
        this.configPath = "config.json";
    }

    public static void main(String[] args) {
        try
        {
            GameManager gameManager = new GameManager();
            gameManager.start();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
