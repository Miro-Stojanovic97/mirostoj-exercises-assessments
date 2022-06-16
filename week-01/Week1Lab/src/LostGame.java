import java.util.Scanner;

public class LostGame {
    // deserted island
    // need to find firewood, lighter and axe to build a fire get rescued and win
    // There's a code on one part of island
    // hidden bunker with computer that needs the code in a certain number of turns otherwise the island blows up

    public static void main(String[] args) {
        String location = "beach";

        boolean hasAxe = false;
        boolean hasFirewood = false;
        boolean hasMatches = false;
        boolean foundBunker = false;

        String code = "4815162342";

        boolean willExplode = true;
        int movesUntilExplosion = 6;

        Scanner console = new Scanner(System.in);
        String command = null;

        System.out.println();
        System.out.println("Shoot, you got lost at sea and are stranded on a deserted island!");
        System.out.println("You need to try and get rescued!");
        System.out.println("The island seems a little creepy...");
        System.out.println();

        while (!location.equals("rescued")) {

            if (movesUntilExplosion > 0) {
                switch (location) {
                    case "beach":
                        // starting area, need firewood and matches to be rescued
                        System.out.println("You're standing on the beach.");
                        if (hasFirewood && hasMatches) {
                            System.out.println();
                            System.out.println("You see a ship on the horizon!");
                            System.out.println("You use the wood and matches to make a smoky fire.");
                            System.out.println("The ship sees it and rescues you!");
                            System.out.println();
                            System.out.println("Congrats, you survived the island!");
                            location = "rescued";
                            break;
                        }
                        System.out.println("There are WOODS to the east.");
                        System.out.println("CAVES to the west.");
                        System.out.println("And BLUFFS to the north.");
                        System.out.println();
                        System.out.print("Which part of the island do you want to explore?   ");

                        command = console.nextLine();
                        if (command.equalsIgnoreCase("woods")) {
                            location = "woods";
                            System.out.println();
                        } else if (command.equalsIgnoreCase("caves")) {
                            location = "caves";
                            System.out.println();
                        } else if (command.equalsIgnoreCase("bluffs")) {
                            location = "bluffs";
                            System.out.println();
                        } else {
                            System.out.println("That's not an option.");
                            System.out.println();
                        }
                        if (willExplode) {
                            movesUntilExplosion -= 1;
                        }
                        break;

                    case "woods":
                        // requires axe to get firewood
                        System.out.println("You're deep in the woods.");
                        System.out.println("There are some nice oak trees you could CHOP down for firewood.");
                        System.out.println("The BEACH is back where you came from.");
                        System.out.print("What do you want to do?   ");

                        command = console.nextLine();
                        if (command.equalsIgnoreCase("chop")) {
                            if (hasAxe) {
                                System.out.println("You chop down a tree. You now have firewood! That seems useful.");
                                hasFirewood = true;
                            } else {
                                System.out.println("You can't chop down a tree with your bare hands!");
                                System.out.println("Maybe find something to chop it down.");
                            }
                        } else if (command.equalsIgnoreCase("beach")) {
                            location = "beach";
                        } else {
                            System.out.println("That's not an option.");
                        }
                        if (willExplode) {
                            movesUntilExplosion -= 1;
                        }
                        break;

                    case "caves":
                        // skeleton with code and matches
                        System.out.println("You've made it to the cave.");
                        System.out.println("*gasp* You notice the skeleton of.. a soldier?");
                        System.out.println("The BEACH is back where you came from.");
                        System.out.print("What do you want to do? INSPECT? Go Back?   ");

                        command = console.nextLine();
                        if (command.equalsIgnoreCase("inspect")) {
                            System.out.println("You find matches. And a piece of paper");
                            System.out.println("It has.. a code?");
                            System.out.println("It says: 4815162342");
                            System.out.println("I'm probably gonna want to copy this down...");
                            hasMatches = true;
                        } else if (command.equalsIgnoreCase("beach")) {
                            location = "beach";
                        } else {
                            System.out.println("That's not an option.");
                        }
                        movesUntilExplosion -= 1;
                        break;

                    case "bluffs":
                        // axe and bunker
                        System.out.println("You've reached the bluffs.");
                        if (!foundBunker) {
                            System.out.println("Whoa! You found.. a BUNKER?");
                        }
                        if (!hasAxe) {
                            System.out.println("You see an axe lying around next to it too. You could TAKE it.");
                        }
                        System.out.println("The BEACH is back where you came from.");
                        System.out.println("What do you want to do?");

                        command = console.nextLine();
                        if (command.equalsIgnoreCase("bunker")) {
                            location = "bunker";
                            foundBunker = true;
                        } else if (command.equalsIgnoreCase("take")) {
                            System.out.println("You now have an axe!");
                            System.out.println();
                            hasAxe = true;
                        } else if (command.equalsIgnoreCase("beach")) {
                            location = "beach";
                        } else {
                            System.out.println("That's not an option.");
                        }
                        break;

                    case "bunker":
                        // enter code to stop explosion
                        System.out.println("You're inside the bunker.");
                        System.out.println("You see a computer in front of you that you can type on.");
                        System.out.println("Whoa, it's on! It says, ENTER: Abort Sequence? What's that all about?");
                        System.out.println("The BLUFFS are back where you came from.");
                        System.out.print("What do you want to do?   ");

                        command = console.nextLine();
                        if (command.equalsIgnoreCase("enter")) {
                            System.out.println("ENTER: Abort Sequence:     ");
                            command = console.nextLine();
                            if (command.equalsIgnoreCase("4815162342")) {
                                System.out.println("The screen says: 'ISLAND RESET CANCELLED'. That's odd");
                                System.out.println("You better get away from this place.");
                                System.out.println();
                                willExplode = false;
                            }
                            else {
                                System.out.println("The screen says: 'ABORT CODE NOT ACCEPTED'.");
                                System.out.println("Maybe I can find something around the Island...");
                            }
                        } else if (command.equalsIgnoreCase("bluffs")) {
                            location = "bluffs";
                        } else {
                            System.out.println("That's not an option.");
                        }
                        if (willExplode) {
                            movesUntilExplosion -= 1;
                        }
                        break;
                 }
            }
            else {
            System.out.println("BFFFFFFFSHHHHOOOOOOOOOMMMMMMMMM");
            System.out.println("The Island exploded and you are dead.");
            System.out.println("Sorry.");
            location = "rescued";
             }
         }
    } //main
} //public class





















