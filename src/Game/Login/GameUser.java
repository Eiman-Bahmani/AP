package Game.Login;
import Game.model.*;
import java.io.Serializable;

public class GameUser  implements Serializable{
    private String username;
    private String password;
    private int id;
    private GameOfThrones game;
    private Kingdom kingdom;
    public Boolean isInGame;

    public GameUser(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
        isInGame=false;
        game=null;
        kingdom=null;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public int getId(){
        return this.id;
    }
}
