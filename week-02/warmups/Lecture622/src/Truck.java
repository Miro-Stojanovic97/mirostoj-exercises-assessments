public class Truck implements Vehicle  {

    private int milesPerHour = 0;

    @Override
    public void accelerate() {
        milesPerHour = milesPerHour - 10;
    }
    @Override
    public void decelerate() {
        milesPerHour = milesPerHour - 10;
    }

    @Override
    public String transport(String materials) {

        return materials;
    }

    @Override
    public void turn() {

    }
}
