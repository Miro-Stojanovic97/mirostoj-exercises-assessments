public class TabletopGame extends Game {
    private int numOfPieces;
    private String board;
    private String dice;
    private String rules;

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void setNumOfPieces(int numOfPieces) {
        this.numOfPieces = numOfPieces;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getDice() {
        return dice;
    }

    public void setDice(String dice) {
        this.dice = dice;
    }

    public TabletopGame(String player1, String player2, String title, int numOfPieces) {
        super(player1, player2, title);
        this.numOfPieces = numOfPieces;
    }

    @Override
    public void setRules(String rules) {
        rules = "New rules for tabletop";
        super.setRules(rules);

    }
}
