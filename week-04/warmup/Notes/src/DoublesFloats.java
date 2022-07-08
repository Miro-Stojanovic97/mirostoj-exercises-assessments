public class DoublesFloats {

    public static void main(String[] args) {

        //results in incorrect answers. 3.000004, .0100009, 28.7999997
        //don't use doubles for calculations
        double a = 0.1 + 0.2;
        double b = 2.0 - 1.99;
        double c = 12 * 2.40;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("------");
        //results in incorrect answers. Floats dont handle small changes for large numbers
        //didnt account for the added pennies
        float revenueF = 3500000000000f;
        System.out.println(revenueF);
        for (int i = 0; i < 300000000; i++) {
            revenueF += 0.01f;
        }
        System.out.println(revenueF);
        System.out.println("---");

        double revenueD = 3500000000000.0;
        System.out.println(revenueD);
        for (int i = 0; i < 300000000; i++) {
            revenueD += 0.01;
        }
        System.out.println(revenueD);


    }




}
