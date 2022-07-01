public interface Vehicle {
    void accelerate();
    void decelerate();
    String transport(String materials);
    void turn() {
        System.out.println("Vehicle is turning");
    }

}
