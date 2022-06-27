// Name: Miro Stojanovic
// Assessment 2 - Gomoku

package learn.gomoku.ui;
import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.RandomPlayer;
import learn.gomoku.players.Player;

import java.util.Scanner;

public class GameController {
    Scanner console = new Scanner(System.in); //initiate for user input
    Player player1; //initiate player variables
    Player player2;
    boolean isActive;
    String line1 = "-------------------------------------------";
    String line2 = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    void run()      {
        int resultCounter = 0;
        isActive = true;
        // only run while game is active
        while (isActive) {
            //setup game
            setup();
            Gomoku game = new Gomoku(player1, player2);

            //Fills board using WIDTH
            Board board = new Board(Gomoku.WIDTH);
            System.out.println("Randomizing first player to move...");
            System.out.println(line1);
            System.out.println(game.getCurrent().getName() + " has the first move!");
            System.out.println();

            do {
                board.printBoard();
                Player currentPlayer = game.getCurrent(); //determine current player
                System.out.printf("It is %s's Turn%n", currentPlayer.getName());

                // create stone using getStone() or generateMove() depending on instance of currentPlayer
                Stone playerStone = currentPlayer instanceof HumanPlayer ? getStone(game.isBlacksTurn()) : currentPlayer.generateMove(game.getStones());
                //pass instance of stone to place() method in Gomoku.java
                Result turnResult = game.place(playerStone);

                // // only populate the board if the turn was successful
                if (turnResult.isSuccess()) {
                    board.populateBoard(game.getStones());
                    System.out.println("Move was successful.");
                    System.out.println();
                    if (game.isOver()) {
                        System.out.println(turnResult.getMessage());
                    }
                }
                else {
                    resultCounter++;
                    if (resultCounter < 3) {
                        System.out.println(turnResult.getMessage());
                        System.out.println();
                    } else {
                        System.out.println(line1);
                        if (currentPlayer instanceof HumanPlayer) {
                            System.out.printf("You are repeatedly breaking rules. Would you like to quit this game?%n" +
                                    "1. Yes%n" +
                                    "2. No%n");
                            int keepPlaying = Integer.parseInt(console.nextLine());
                            if (keepPlaying == 1) {
                                break;
                            } else {
                                resultCounter = 0;
                            }
                        }
                    }
                }

            } while (!game.isOver());
            // Final board print after game ends.
            board.printBoard();
            isActive = playAgain();
        }
    }

    void setup() {
        System.out.println(line2);
        System.out.println("|     Hello. Welcome to Gomoku!    |");
        System.out.println(line2);
        player1 = getPlayerClass("Player 1");
        System.out.println(line1);
        player2 = getPlayerClass("Player 2");
    }

    public Player getPlayerClass(String player) {
        System.out.printf("Is %s a human or randomized CPU [?]: %n" +
                "1. Human%n" +
                "2. CPU%n", player);

        switch(console.nextLine()) {
            case "1":
                System.out.printf("Please enter your name %s: ", player);
                return new HumanPlayer(console.nextLine()); //instantiates human player
            case "2":
                return new RandomPlayer(); //instantiates random player
            default:
                return getPlayerClass(player);
        }
    }

    public Stone getStone(boolean isBlack) {
        int row = 20;
        int column = 20;
        System.out.print("Enter the row for your move: ");
        try {
            row = Integer.parseInt(console.nextLine()) - 1 ;
        } catch (NumberFormatException e) {
            System.out.println("Please enter integers only. Try again. ");
        }
        System.out.print("Enter the column for your move: ");
        try {
            column = Integer.parseInt(console.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Please enter integers only. Try again. ");
        }
        return new Stone(row, column, isBlack);
    }

    public boolean playAgain() {
        System.out.print("Game over. Play again? [y/n]: ");
        switch(console.nextLine()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                return playAgain();
        }
    }
}


