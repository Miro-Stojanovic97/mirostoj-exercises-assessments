package learn.battle.fighters;

public class Fighter {

    private final String name;
    private int health = 100;

    public Fighter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isOut() {
        return health <= 0;
    }

    public void reduceHealth(int amount) {
        health -= amount;
    }
    public void increaseHealth(int amount) {
        health += amount;
    }
}
