public class App {
    public static void main(String[] args) {
        VideoGame videoGame = new VideoGame("Player 1", "Player 2", "FIFA 23", 3);
        videoGame.setPublisher("EA Sports");

        TabletopGame chessGame = new TabletopGame("White", "Brown", "Chess", 32);
        chessGame.setBoard("8x8");
        chessGame.setNumOfPieces(32);
        chessGame.setGenre("Board game");

        printToScreen(videoGame);
        printToScreen(chessGame);
    }

    private static void printToScreen(Game game){
        if(game instanceof Game)
            System.out.println("This is only a game");
        if(game instanceof VideoGame)
            System.out.println("This is a video game");
        if(game instanceof TabletopGame)
            System.out.println("This is a tabletop game");

        System.out.println(game.getTitle());
    }
}
