public class Hero {
    private String name;
    private Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }

    public String getName() {
        return name;
    }

    public Power[] getPowers() {
        return powers;
    }

    // Description: returns the Hero's name and powers as a single line of text.
    public String toLine() {
        Power[] powers = getPowers();
        String[] powerNames = new String[powers.length];
        for(int i=0; i < powers.length; i++) {
            powerNames[i] = powers[i].getPowerName();
        }
        return String.format("%s (%s)", getName(), String.join(",", powerNames));
    }
}
