public class VideoGame extends Game {  //extends inheritence
    private int numControllers;
    private String platformType;
    private String publisher;

    public VideoGame(String player1, String player2, String title, int numControllers) {
        super(player1, player2, title);
        this.numControllers = numControllers;
    }
    public int getNumControllers() {
        return numControllers;
    }

    public void setNumControllers(int numControllers) {
        this.numControllers = numControllers;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
