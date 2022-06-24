package learn.gomoku.ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.game.Result;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;
import learn.gomoku.players.HumanPlayer;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Menu {
    private Scanner console;

    public void greeting(){
        System.out.println("~*~*~*~*~*~*~");
        System.out.println("~*~*~*~Welcome to Gomoku!~*~*~*~");
        System.out.println("~*~*~*~*~*~*~");
    }

    public void setup(){
        greeting(); //Perhaps?
        do {
            //Play the game?
        } while (//we still want to play);
        }

    public void getPlayer() {
        System.out.println("Is this a human (input 1) or random player (input 2)?");
        //get input and then make a decision based on player type
        String response = console.nextLine();

        switch (response) {
            case "1": humanPlayerSetup();
                break;
            case "2": randomPlayerSetup();
                break;
            default:
                System.out.println("I don't recognize that input");
                break;

        }
    }
    public void humanPlayerSetup(){
        System.out.println("Please enter a name:  ");
        //collect name form my human player
        //create HumanPlayer object w name property data provided
    }
    public void randomPlayerSetup() {
        //Instantiate random player
    }

    public void gameSetup(Player playerOne, Player playerTwo) {
        //playerOne object, playerTwo object
        Gomoku gomoku = new Gomoku(playerOne, playerOne);

        System.out.println("Choosing first player randomly...");
        System.out.println("...");
    }

}
