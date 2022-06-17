import java.util.Scanner;

public class Exercise10 {

    public static void finalMessage(String price) {
        System.out.printf("The price is: %s%n", price);
    }
    public static String errorMessage() {
        return "Something went wrong";
    }
        public static void main (String[]args){
            // USPS
            // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

            // 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
            //will need a scanner
            //collect integers, in a string, may need to convert to int
            //will save user input to variables
            // 2. Add `if`/`else if`/`else` logic to cover all rates.
            //if (Does Zone ==1 || Does Zone ==2) {}
            // switch for the poundage
            //else if (Zone == 3) {}
            //switch for poundage
            //else{
            //    Refusal message
            //}
            // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
            // If a lbs/zone combo does not exist, print a warning message for the user.

            Scanner console = new Scanner(System.in);

            System.out.println("Which zone are you in? Please choose 1, 2, or 3");
            Integer zone = Integer.parseInt(console.nextLine());
            System.out.println("How much does the package weigh? between 1-5lbs");
            Integer lbs = Integer.parseInt(console.nextLine());

            if (zone == 1 || zone == 2) {
                // switch for the lbs
                String priceMessage = "The price is: ";
                switch (lbs) {
                    case 1:
                        System.out.printf("%s$7.50%n", priceMessage);
                    case 2:
                        System.out.printf("%s$8.25%n", priceMessage);
                    case 3:
                        System.out.printf("%s$8.70%n", priceMessage);
                    case 4:
                        System.out.printf("%s$9.20%n", priceMessage);
                    case 5:
                        System.out.printf("%s$10.20%n", priceMessage);
                        break;
                }
            } else if (zone == 3) {
                switch (lbs) {
                    case 1:
                        finalMessage("$7.85");
                    case 2:
                        finalMessage("$8.70");
                    case 3:
                        finalMessage("$9.70");
                    case 4:
                        finalMessage("$10.55");
                    case 5:
                        finalMessage("$11.30");
                    default:
                        System.out.println("Idk what to do w that");
                        break;
                }
            }
            else {
                System.out.println(errorMessage());
            }
        }
}
