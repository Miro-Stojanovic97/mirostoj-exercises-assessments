public class Game {
    private String player1;
    private String player2;
    private String winner;
    private String rules;
    private String genre;
    private String setting;
    private String title;

    public Game(String player1, String player2, String title) {
        this.player1 = player1;
        this.player2 = player2;
        this.title = title;
    }

    public String getPlayers() {
        return this.player1 + " " + this.player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
