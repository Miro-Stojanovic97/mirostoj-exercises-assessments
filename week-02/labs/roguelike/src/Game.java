import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    private final static String WALL_CHARACTER = "|";
    private final static String TopBot_WALL_CHARACTER = "=";
    private final static String EMPTY_CHARACTER = " ";
    private final Scanner console = new Scanner(System.in);
    //System.out.println("How big should the map be [X by X]?: ");
    private static int WIDTH = 0;
    private static int treasureCount=0;
    private Hero hero;
    private Monster monster;
    private Treasure treasure1;
    private Treasure treasure2;
    private Trap trap;
    private boolean isOver;

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
        printWorld();
    }

    private void setUp() {
        System.out.print("What is the name of your hero?: ");
        String name = console.nextLine();
        System.out.print("What would you like your symbol to be?: ");
        char symbol = console.nextLine().charAt(0);
        System.out.print("What should the map size be [X by X]?: ");
        WIDTH = Integer.parseInt(console.nextLine());

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);
        hero = new Hero(name, x, y, symbol);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());
        treasure1 = new Treasure(x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());
        treasure2 = new Treasure(x, y);

        do{
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == (hero.getX()) || (x==treasure1.getX()));
        monster = new Monster(x,y);

        do{
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == (hero.getX()) || (x==treasure1.getX()) || x == (monster.getX()));
        trap = new Trap(x,y);
        }



    private void printWorld() {
        // top wall border
        System.out.println(TopBot_WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            // left wall border
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                if (row == hero.getY() && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure1.getY() && col == treasure1.getX()) {
                    System.out.print("T");
                } else if (row == treasure2.getY() && col == treasure2.getX()) {
                    System.out.print("T");
                } else if (row == monster.getY() && col == monster.getX()) {
                    System.out.print(monster.getSymbol());
                }
                else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }

        // bottom wall border
        System.out.println(TopBot_WALL_CHARACTER.repeat(WIDTH + 2));
    }

    private void move() {
        System.out.print(hero.getName() + ", move [WASD]: ");
        String move = console.nextLine().trim().toUpperCase();

        monsterMove();

        if (move.length() != 1) {
            return;
        }

        switch (move.charAt(0)) {
            case 'W':
                hero.moveUp();
                break;
            case 'A':
                hero.moveLeft();
                break;
            case 'S':
                hero.moveDown();
                break;
            case 'D':
                hero.moveRight();
                break;
        }


        if (hero.getX() < 0 || hero.getX() >= WIDTH
                || hero.getY() < 0 || hero.getY() >= WIDTH) {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        } else if (hero.getX() == treasure1.getX() && hero.getY() == treasure1.getY()) {
            System.out.println(hero.getName() + " found the treasure!");
            treasureCount = treasureCount +1;
            if (treasureCount == 2) {
                System.out.println(hero.getName() + " found both Treasures! You win.");
                isOver = true;
            }

        } else if (hero.getX() == treasure2.getX() && hero.getY() == treasure2.getY()) {
            System.out.println(hero.getName() + " found the treasure! ");
            treasureCount = treasureCount +1;
            if (treasureCount == 2) {
                System.out.println(hero.getName() + " found both Treasures! You win.");
                isOver = true;
            }
        }
        else if(hero.getX()==monster.getX() && hero.getY()== monster.getY()){
            System.out.println(hero.getName() + " was eaten by the Monster! You lose.");
            isOver = true;
        } else if(hero.getX()==trap.getX() && hero.getY()==trap.getY()){
            System.out.println(hero.getName()+" fell in a trap! You lose.");
            isOver = true;
        }
    }

    private void monsterMove() {
        Random rand = new Random();
        int randomMove = rand.nextInt(4);
        if (monster.getY() <= 2) {
            if (randomMove == 1) {
                randomMove = 2;
            }
        } else if (monster.getY() >= WIDTH-1) {
            if (randomMove == 2) {
                randomMove = 1;
            }
        } else if (monster.getX() <= 2){
            if (randomMove == 3) {
                randomMove = 4;
            }
        }else if(monster.getX() >= WIDTH-1){
            if (randomMove == 4){
                randomMove=3;
            }
        }

        switch (randomMove){
            case 1:
                monster.moveUp();
                break;
            case 2:
                monster.moveDown();
                break;
            case 3:
                monster.moveLeft();
                break;
            case 4:
                monster.moveRight();
                break;
        }
    }
}
