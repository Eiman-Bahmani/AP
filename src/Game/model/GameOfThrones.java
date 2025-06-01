package Game.model;
import java.io.Serializable;

import Game.Login.*;


public class GameOfThrones implements Serializable {
    private  static final int maxPlayer=3;

    protected static final long serialVersionUID = 1L;
    protected int id;
    protected  String name;
    protected  String filename;
    protected  Map map;
    protected GameUser[] User;
    protected int numberOfPlayer;
    protected int password;
    protected  boolean gameReady;

    public int getId() {
        return id;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameUser[] getUser() {
        return User;
    }

    public int getPassword() {
        return password;
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public String getName() {
        return name;
    }

    public  int getMaxPlayer() {
        return numberOfPlayer;
    }

    public   boolean addPlayer(GameUser user){
        if (numberOfPlayer>=maxPlayer){
            this.gameReady=true;
            return false;
        }
        if(numberOfPlayer==(maxPlayer-1)){
        this.User[numberOfPlayer++]=user;
        user.isInGame=true;
        //user.game=this;
        // مشکل در ایدی نامGameFileUtility.deleteGame(this.name,this.gameId);
            this.gameReady=true;
GameFileUtility.updateGame(this);
        return true;
        }
    this.User[numberOfPlayer++]=user;
    user.isInGame=true;
    //user.game=this;

        GameFileUtility.updateGame(this);

    return true;
}

    public GameOfThrones(String name,int pass ,GameUser user){
        User=new GameUser[maxPlayer];
        user.isInGame=true;

        User[0]=user;
        this.name=name;
        password=pass;
        this.numberOfPlayer=1;
        this.gameReady=false;
    }
}
