public class Monster {
    private final char symbol = 'X';
    private int x;
    private int y;

        // Create a hero with a name and an initial position.
    public Monster(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // getters

        public char getSymbol() {
            return symbol;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        // movement
        public void moveLeft() {
            x--;
        }

        public void moveRight() {
            x++;
        }

        public void moveUp() {
            y--;
        }

        public void moveDown() {
            y++;
        }
}
