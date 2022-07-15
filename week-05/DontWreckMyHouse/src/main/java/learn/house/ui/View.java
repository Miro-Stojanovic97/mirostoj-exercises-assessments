//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

        Controller.sleep(350);

            //---print menu options to user (option number and message)
        for (MenuOption o : MenuOption.values()) {
            io.printf(" %s.)  %s%n", o.getNumber(), o.getMessage());
        }

        System.out.println();

            //---prompt user to choose option
        String message = String.format("Choose an option [%s - %s]:  ", minOption, maxOption);
        System.out.println();

            //---return menu option from value. Validate input in ConsoleIO: confirm in-range, confirm int, confirm input
        return MenuOption.fromValue(io.readInt(message, minOption, maxOption));

    }

    public void printTitle(String message) {
        for(int t = 0; t < 7; t++) {
            System.out.println(" ".repeat(t) + "🏠\\\\🏠");
            Controller.sleep(100);
        }
        //---console title formatting
        String openingTitle = " 🏠 " + message + " 🏠 ";

        io.println("╭" + "━".repeat(openingTitle.length() - 1) + "╮");
        io.println("│" + openingTitle + "│");
        io.println("╰" + "━".repeat(openingTitle.length() - 1) + "╯");

    }

    public void printMainTitle(String message) {
        //---console title formatting
        String openingTitle = " 🏠 " + message + " 🏠 ";

        io.println("╭" + "━".repeat(openingTitle.length() - 1) + "╮");
        io.println("│" + openingTitle + "│");
        io.println("╰" + "━".repeat(openingTitle.length() - 1) + "╯");
        Controller.sleep(1000);
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
            io.println("No reservations found.");
            return;
        }

        displayHostAndLocation(reservations.get(0).getHost());

        String format = "%-4s%-13s%-13s%-15s%-15s%-25s";
        String header = String.format(format, "ID", "Date In", "Date Out", "First Name", "Last Name", "Email");
        io.println(header);
        io.println("-".repeat(header.length()));

        for (Reservation r : reservations) {
            io.printf(format,
                    r.getReservationId(),
                    r.getStartDate().toString(),
                    r.getEndDate().toString(),
                    r.getGuest().getFirstName(),
                    r.getGuest().getLastName(),
                    r.getGuest().getEmail()
            );
            io.print("\n");
        }
        System.out.println();
        io.readString("Press ENTER to continue:  ");
    }

    public void displayHostAndLocation(Host host) {
        printTitle(host.getLastName() + ": " +
                host.getCity() + ", " + host.getState());
    }

    public Reservation makeReservation(Host host, Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setGuestId(guest.getGuestId());
        reservation.setHost(host);
        reservation.setHostId(host.getHostId());
        reservation.setStartDate(io.readLocalDate("Start Date [yyyy-MM-dd]: "));
        reservation.setEndDate(io.readLocalDate("End Date [yyyy-MM-dd]: "));
        reservation.calculatePriceTotal();
        io.println("Total: $" + reservation.getPriceTotal().toString());
        return reservation;
    }

    public Reservation chooseReservation(List<Reservation> reservations) {
        displayReservations(reservations);
        Reservation result = null;
        if (reservations.size() > 0) {
            do {
                int reservationId = io.readInt("Choose a reservation ID: ",
                        reservations.get(0).getReservationId(),
                        reservations.get(reservations.size() - 1).getReservationId());
                for (Reservation r : reservations) {
                    if (r.getReservationId() == reservationId) {
                        result = r;
                        break;
                    }
                }
                if (result == null) io.println("Not a valid ID.");
            } while (result == null);
        }
        return result;
    }

    public Reservation editReservation(Reservation reservation) {
        printTitle("Update Reservation");

        LocalDate startDate = io.readLocalDate("Start Date (" + reservation.getStartDate() + "): ");
        // only update if it changed
        if (startDate.toString().length() > 0) {
            reservation.setStartDate(startDate);
        }

        LocalDate endDate = io.readLocalDate("End Date (" + reservation.getEndDate() + "): ");
        // only update if it changed
        if (endDate.toString().length() > 0) {
            reservation.setEndDate(endDate);
        }

        return reservation;
    }

    public boolean confirmReservationSummary(Reservation reservation) {
        printTitle("Reservation Summary");
        String format = "Start:\t%s%n" +
                "End:\t%s%n" +
                "Total:\t%s%n";

        io.printf(format, reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                "$" + reservation.getPriceTotal().toString());

        while (true) {
            String selection = io.readRequiredString("Is this okay? [Y/n]: ");
            switch (selection.toLowerCase()) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    io.println("Not a valid selection");
            }
        }
    }




}
