package Game.Login;

import Game.model.GameOfThrones;

import java.util.Scanner;

public class Game {
    Scanner input=new Scanner(System.in);
    public static void main(GameUser user, GameOfThrones game) {
        boolean exit=false;
        if(!game.isGameReady())
            exit=loadingPage(user,game);
        else
            exit=startGame(user,game);


        if (exit)
            System.exit(0);
    }

    public   static boolean loadingPage(GameUser user,GameOfThrones game){
        Scanner input=new Scanner(System.in);

        System.out.println("PLEASE WAIT FOR EVERYONE JOIN THE GAME "+game.getMaxPlayer());
       String choice=null;
       while (!game.isGameReady()){

       }
       if(game.isGameReady())
           return startGame(user,game);
       return true;
    }
    public static boolean startGame(GameUser user,GameOfThrones game){
        //todo کد ها و منو های درونی گیم
        System.out.println("hours dicks in your mouths guys");
        return true;
    }
}
