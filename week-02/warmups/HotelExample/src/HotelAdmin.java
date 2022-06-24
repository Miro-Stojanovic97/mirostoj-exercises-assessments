import java.util.Scanner;

public class HotelAdmin {
    // all of the hotel operations done in here
    public static void main(String[] args) {
        System.out.println("Welcome to the Capsule Hotel!\n=============================");
        int numCapsules = readValidInt("Please enter the number of capsules: ");
        // String[] capsules = new String[numCapsules];
        Hotel myHotel = new Hotel("Dev10Hotel", numCapsules);
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

    public static void checkIn(Hotel hotel) {
        System.out.printf("\nCheck in");
        String firstName = readString("Enter first name:");
        String lastName = readString("Enter last name:");
        Guest guest = new Guest(firstName, lastName);

        String roomInput = String.format("Enter a room num.: ", hotel.getHotelCapacity());
        int roomNum = readValidInt(roomInput);
        Room room = new Room(roomNum);
        room.setGuest(guest);
        System.out.println("\nSuccess. They were booked.");
        Room[] rooms    = new Room[hotel.getHotelCapacity()];
        rooms[room.getRoomNumber() - 1] = room;
        hotel.setRooms(rooms);
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
    public static String readValidInt(String input) {
        String output;
        int result = 0;
        do{
            output = readString(input);
            if (!stringCanBeInt(output)) {
                System.out.printf("Error. enter valid integer");
                continue;
            }
        } while (){

        }
    }
}