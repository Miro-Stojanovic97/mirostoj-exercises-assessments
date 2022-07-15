//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;

import learn.house.models.Reservation;
import org.springframework.stereotype.Component;
import java.util.List;
import java.awt.*;

@Component
public class View {

        //---instantiating instance 'io' of ConsoleIO class
    private final ConsoleIO io;

        //---view constructor---
    public View(ConsoleIO io) { this.io = io; }

        //---choose menu option from list
    public MenuOption chooseMenuOption() {
        printTitle("Main Menu"); //print menu title in special format
        int minOption = 0;
        int maxOption = MenuOption.values().length - 1;

            //---print menu options to user (option number and message)
        for (MenuOption o : MenuOption.values()) {
            io.printf("%s.)  %s%n", o.getNumber(), o.getMessage());
        }

            //---prompt user to choose option
        String message = String.format("Choose an option [%s - %s]:  ", minOption, maxOption);

            //---return menu option from value. Validate input in ConsoleIO: confirm in-range, confirm int, confirm input
        return MenuOption.fromValue(io.readInt(message, minOption, maxOption));
    }

    public void printTitle(String message) {
            //---console title formatting
        String openingTitle = "\uD83C\uDFE0" + message + "\uD83C\uDFE0";
        io.println("\n".repeat(10) + "╭" + "━".repeat(openingTitle.length() - 1) + "╮");
        io.println("│" + openingTitle + "│");
        io.println("╰" + "━".repeat(openingTitle.length() - 1) + "╯");
    }

    public void printException(Exception ex) {
        printTitle("System Error: ");
        io.println(ex.getMessage());
    }

    public void displayStatus(boolean success, String message) { displayStatus(success, List.of(message)); }

    public void displayStatus(boolean success, List<String> messages) {
        printTitle(success ? "Successful!" : "Error!");
        for (String message : messages) {
            io.println(message);
        }
    }

    public String getEmail(String user) { return io.readRequiredString(user + " email:  "); }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations were found.");
            return;
        }
    }

}
