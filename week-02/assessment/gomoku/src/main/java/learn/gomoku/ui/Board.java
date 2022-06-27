// Name: Miro Stojanovic
// Assessment 2 - Gomoku

package learn.gomoku.ui;
import learn.gomoku.game.Stone;
import java.util.List;

public class Board {
    public final int width;
    public char[][] board;

    public Board(int width) {
        //create new board filled with default character
        this.width = width;
        board = new char[width][width];
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = '_';
            }
        }
    }

    public void populateBoard(List<Stone> stones) {
        for (int row = 0; row < width; row ++) {
            for (int col = 0; col < width; col++) {
                //from online -> for(dataType variable : collection | array)
                for (Stone stone : stones) {
                    //place correct stone char depending on player class
                    if (stone.getColumn() == col && stone.getRow() == row) {
                        if (stone.isBlack()) {
                            board[row][col] = 'B';
                        } else {
                            board[row][col] = 'W';
                        }
                    }
                }
            }
        }
    }

    public void printBoard() {
        //prints column coordinates
        for (int i = 0; i <= width; i++) {
            if (i != 0) System.out.printf("%02d ", i);
            else System.out.print(" ".repeat(3));
        }

        System.out.println();
        for (int i = 0; i < width; i++) {
            System.out.printf("%02d  ", i + 1); //format ##, e.g. 03 instead of 3
            for (int j = 0; j < width; j++) {
                System.out.printf("%s  ", board[i][j]); //prints out full board
            }
            System.out.println(); //starts each new row
        }
    }

}
