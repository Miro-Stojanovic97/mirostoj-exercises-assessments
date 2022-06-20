//Author: Miro Stojanovic
//Module 1 Assessment

import java.util.Scanner;
public class PodHotelApp {

    public static void main(String[] args) {
        String line = "------------------------------------------------------------"; //console formatting line
        System.out.println(line);
        System.out.println("Welcome to PodManager, by MiroDev, LLC. ;)"); //Intro to App
        System.out.printf("<> Update 2.1 has unveiled a new feature called PodViewer%n%n");
        System.out.println("Please enter the current hotel capacity: ");

        Scanner console = new Scanner(System.in); //Prompt user for hotel capacity and read from console
        int podAvail = Integer.parseInt(console.nextLine());

        String[] pods = new String[podAvail];    //initiate string array for pods String[]
        System.out.println(new String(new char[5]).replace("\0", ".\r\n")); //console formatting

        String input;
        //User chooses check-in, check-out, podViewer, or exit within do/while loop
        do {
            System.out.println(line);
            System.out.println("Welcome to Main Menu. This hotel has " + podAvail + " pods");  //initiate menu for user / Print number of pods vacant - podAvail
            System.out.printf("1. Check-In%n" + "2. Check-Out%n" + "3. PodViewer%n" + "4. Exit%n");
            System.out.println("Please choose one of the above options [1-4]:  ");
            input = console.nextLine(); //Prompt user to choose an option and read from console

            System.out.println(new String(new char[5]).replace("\0", ".\r\n"));
            System.out.println(line);

            if (input.equals("1")) {
                checkInOut(input, pods, console); //check-in method
            } else if (input.equals("2")) {
                checkInOut(input, pods, console); //check-out method
            } else if (input.equals("3")) {
                podDisplay(pods, console); //podViewer method
            } else if (input.equals("4")) {
                System.out.println("You have exited the app. Have a great day!"); //Exit app -> Goodbye
            } else {
                System.out.println("This is not an option."); //Tell user if they don't choose one of the options
            }

        } while (!input.equals("4")); //when option 4 is chosen, exit the do loop, exiting the app.

    } //end of main method

    //define one method for checking in and checking out using input, pods array, and the console
    static void checkInOut(String input, String[] pods, Scanner console) {
        //if input = 1, initiate the check-in menu. Prompt for which pod is being checked-in to.
        if (input.equals("1")) {
            System.out.println("GUEST CHECK-IN:");
            System.out.println("What is the pod number you are checking a guest into?  ");
            int roomIndex = Integer.parseInt(console.nextLine()) - 1;
            if (roomIndex < 0 || roomIndex >= pods.length) {
                System.out.println("That was out of the range.");
                return;
            }
            //Only check-in new guest if that pod is empty i.e. null
            if (pods[roomIndex] == null) {
                System.out.println("What is the name of the new guest?");
                String name = console.nextLine();
                pods[roomIndex] = name;
                System.out.printf("Guest " + name + " has been successfully checked in to pod #" + (roomIndex + 1) + "%n%n");
                //If the pod isn't empty, tell the user
            } else {
                System.out.println("This pod is already occupied. Please choose another pod, or check out the existing guest.");
                System.out.println();
                return;
            }
        } else {
            //if input =/= 1, initiate the check-out menu. Prompt for which pod is being checked-out of.
            System.out.println("GUEST CHECK-OUT:");
            System.out.println("What is the pod number you are checking a guest out of?  ");
            int roomIndex = Integer.parseInt(console.nextLine()) - 1;
            if (roomIndex < 0 || roomIndex >= pods.length) {
                System.out.println("That was out of the range.");
                return;
            }
            //Only check-out pods that are not empty i.e. !null
            if (pods[roomIndex] != null) {
                String name = pods[roomIndex];
                System.out.printf("Guest " + name + " has been successfully checked out of pod #" + (roomIndex + 1) + "%n%n");
                pods[roomIndex] = null;
                //If the pod is empty, tell the user
            } else {
                System.out.println("This pod is already vacant. Please choose another pod, or check in a new guest.");
                System.out.println();
                return;
            }
        }
    }

    //define method for displaying pods, 5 above and 5 below the requested pod #
    static void podDisplay(String[] pods, Scanner console) {
        //initiate podViewer menu
        System.out.println("Pod-Viewer:");
        System.out.println("What pod would you like to view [#]?  ");
        int roomIndex = Integer.parseInt(console.nextLine()) - 1;
        System.out.printf("Pod #" + (roomIndex + 1) + " is occupied by " + pods[roomIndex] + "%n%n");
        //if the pod is in range that doesn't need to cycle over zero, print out the pods 5 below and 5 above as normal
        if (roomIndex >= 6 && roomIndex < pods.length - 5) {
            for (int i = roomIndex - 5; i <= roomIndex + 5; i++) {
                System.out.printf("Pod #%s: %s%n",
                        i + 1, pods[i] == null ? "-[Vacant]-" : pods[i]);
            }
        //if the pod is in range that needs to cycle over zero, print out the pods 5 below and 5 above in two parts
        //first print the pods that are toward the end of the array, and then the pods at the start of the array
        } else if (roomIndex < 6) {
            int firstRoom = pods.length - (6 - (roomIndex + 1));
            for (int i = firstRoom; i < pods.length; i++) {
                System.out.printf("Pod #%s: %s%n",
                        i + 1, pods[i] == null ? "-[Vacant]-" : pods[i]);
            }
            for (int i = 0; i <= roomIndex + 5; i++) {
                System.out.printf("Pod #%s: %s%n",
                        i + 1, pods[i] == null ? "-[Vacant]-" : pods[i]);
            }
        //if the pod is in range that needs to cycle over zero, print out the pods 5 below and 5 above in two parts
        //first print the pods that are toward the end of the array, and then the pods at the start of the array
        } else {
            int lastRoom = 6 - (pods.length - (roomIndex - 1));
            for (int i = roomIndex - 5; i < pods.length; i++) {
                System.out.printf("Pod #%s: %s%n",
                        i + 1, pods[i] == null ? "-[Vacant]-" : pods[i]);
            }
            for (int i = 0; i <= lastRoom; i++) {
                System.out.printf("Pod #%s: %s%n",
                        i + 1, pods[i] == null ? "-[Vacant]-" : pods[i]);

            }
        }
    }
}