import java.util.Scanner;

public class WarmupAlex {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your birthday (for example: 05/30):");
        String birthday = console.nextLine();
        if (birthday.charAt(2) == 6) {
            System.out.println("Happy Birthday!");
            if (birthday.equalsIgnoreCase("06/16")) {
                System.out.println("Happy Birthday!");

            } else {
                System.out.println("Happy Birthday Month!");
            }
        }
        else {
            System.out.println("It is not your birthday month");
        }
    }
}
