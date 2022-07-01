public class App {
    public static void main(String[] args) {
        //1. Instantiation
        NationalForest one = new NationalForest("Dixie", "Utah", 123);
        //NationalForest two = new NationalForest("Angeles", "California", 661565);
        //NationalForest three = new NationalForest("Angelina", "Texas", 154140);

        // individual fields and calculations
        System.out.println("Forest Name: " + one.getName());
        System.out.println("Location: " + one.getLocation());
        System.out.println("Acres: " + one.getAcres());
        System.out.println("Square km: " + one.getSquareKilometers());

        // all together
        System.out.println(one.toLine());

        // acres is wrong, fix it
        one.setAcres(1885655);
        System.out.println("Fixed ---");
        System.out.println("Acres: " + one.getAcres());
        System.out.println("Square km: " + one.getSquareKilometers());
    }
}
