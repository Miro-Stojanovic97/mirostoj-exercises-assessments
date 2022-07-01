import java.util.Scanner;

public class CapsuleHotel {
    public static void main(String[] args) {
        System.out.println("Welcome to the Capsule Hotel!\n=============================");
        int numCapsules = readValidInt("Please enter the number of capsules: ");
        String[] capsules = new String[numCapsules];
        boolean exit = false;
        System.out.printf("Guest Menu\n==========\n%s\n%s\n%s\n%s\n",
                "1. Check In",
                "2. Check Out",
                "3. See Guests",
                "4. Exit");
        do {
            int choice = readValidInt("\nPlease choose an option [1 - 4]: ");
            switch (choice) {
                case 1:
                    checkIn(capsules);
                    break;
                case 2:
                    checkOut(capsules);
                    break;
                case 3:
                    showGuests(capsules);
                    break;
                case 4:
                    exit = doExitProcedure();
            }
        } while (!exit);
    }


    // prompts user with a statement -> reads & returns a valid integer (x > 0)
    public static int readValidInt(String input) {
        String output;
        int result = 0;
        do {
            output = readString(input);
            if (!stringCanBeInt(output)) {
                System.out.printf("\nError :(\nPlease enter a valid integer.\n\n");
                continue;
            }
            result = Integer.parseInt(output);
            if (result <= 0) {
                System.out.printf("\nError :(\nValue must be greater than 0.\n\n");
            }
        } while (result <= 0);
        return result;
    }


    // determines if a string can be parsed to an integer
    public static boolean stringCanBeInt(String input) {
        String numbers = "0123456789";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i == 0 && c == '-') {
                continue;
            } else if (numbers.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }


    // prompts user with statement -> reads & returns valid string (length > 0)
    public static String readString(String input) {
        Scanner scanner = new Scanner(System.in);
        String result;
        do {
            System.out.print(input);
            result = scanner.nextLine().trim();
        } while (result.length() == 0);
        return result;
    }


    // checks in a patron to a room if it is valid && empty
    public static void checkIn(String[] capsules) {
        System.out.printf("\nCheck In\n========\n");
        String name = readString("Enter the guest name: ");
        String roomInput = String.format("Enter a room number [1 - %d]: ", capsules.length);
        int room;
        do {
            room = readValidInt(roomInput) - 1;
            if (!roomIsValid(room, capsules)) {
                continue;
            } else if (capsules[room] != null) {
                System.out.printf("\nError :(\n%s is already in capsule #%d.\n\n",
                        capsules[room], room + 1);
                continue;
            }
            capsules[room] = name;
            System.out.printf("\nSuccess :)\n%s booked in capsule #%d\n\n",
                    name, room + 1);
            break;
        } while (true);

    }


    // checks a patron out of a room if the room is not empty
    public static void checkOut(String[] capsules) {
        System.out.printf("\nCheck Out\n=========\n");
        int room;
        String roomInput = String.format("Enter a room number [1 - %d]: ", capsules.length);
        do {
            room = readValidInt(roomInput) - 1;
            if (!roomIsValid(room, capsules)) {
                continue;
            } else if (capsules[room] == null) {
                System.out.printf("\nError :(\nCapsule #%d is already empty.\n\n", room + 1);
                continue;
            }
            System.out.printf("\nSuccess :)\n%s checked out of capsule #%d\n\n",
                    capsules[room], room + 1);
            capsules[room] = null;
            break;
        } while (true);
    }


    /*
     * Prompts the user for a room number and prints out states of each room N
     * rooms before (where N = 5 and is lower bounded by 0) and K rooms ahead
     * (where K = 5 and is upper bounded by capsules.length - 1)
     * */
    public static void showGuests(String[] capsules) {
        System.out.printf("\nShow Guests\n==========\n");
        int room;
        String roomInput = String.format("Enter a room number [1 - %d]: ", capsules.length);

        do {
            room = readValidInt(roomInput) - 1;
        } while (!roomIsValid(room, capsules));

        int lowerBound = room;
        int upperBound = room;

        int difference = upperBound - lowerBound;
        while (difference < 10 && (upperBound + 1 < capsules.length || lowerBound > 0)) {
            lowerBound = lowerBound - 1 >= 0 ? lowerBound - 1 : lowerBound;
            upperBound = upperBound + 1 < capsules.length ? upperBound + 1 : upperBound;
            difference = upperBound - lowerBound;
        }

        System.out.println("\nCapsule : Guest");
        for (int i = lowerBound; i <= upperBound; i++) {
            String guest = capsules[i] != null ? capsules[i] : "[unoccupied]";
            System.out.printf("%d : %s\n", i + 1, guest);
        }
    }


    // checks a room number does not exceed highest room number in capsules
    public static boolean roomIsValid(int room, String[] capsules) {
        if (room >= capsules.length) {
            System.out.printf("\nError :(\nCapsule #%d does not exist.\n\n", room + 1);
            return false;
        }
        return true;
    }


    // exits the application
    public static boolean doExitProcedure() {
        System.out.printf("\nExit\n====\nAre you sure you want to exit?\nAll data will be lost.\n");
        String response;
        do {
            response = readString("Choose an option [y/n]: ");
            switch (response.toLowerCase().charAt(0)) {
                case 'y':
                    return true;
                case 'n':
                    return false;
            }
        } while (true);
    }
}