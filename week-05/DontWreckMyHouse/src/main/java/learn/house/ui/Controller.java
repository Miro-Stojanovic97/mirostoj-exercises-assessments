//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;


//imports for spring annotations
import learn.house.data.DataAccessException;
import learn.house.domain.GuestService;
import learn.house.domain.HostService;
import learn.house.domain.ReservationService;
import learn.house.domain.Result;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.stereotype.Component;


import java.util.List;



@Component
public class Controller {

        //---controller fields---
    private final GuestService guestService;
    private final HostService hostService;
    private final ReservationService reservationService;
    private final View view;

        //---initiate constructor---
    public Controller(GuestService guestService, HostService hostService, ReservationService reservationService, View view) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.reservationService = reservationService;
        this.view = view;
    }

        //---begin run() method---
    public void run() {

            //--opening title screen--
        System.out.println();
        view.printMainTitle("Welcome to Don't Wreck My House!");
        sleep(300);
        for(int t = 0; t < 17; t++) {
            System.out.println(" ".repeat(17-t) + "🏠//🏠");
            sleep(80);
        }

            //--enter main() loop if no exceptions--
        try {
            main();
        } catch (DataAccessException ex) {
            view.printException(ex); //catch exception
        }
        view.printTitle("Closing App...");
    }

    public void main() throws DataAccessException {
        MenuOption option;
        do {
            option = view.chooseMenuOption();
            switch(option) {
                case EXIT:
                    break;
                case VIEW_RESERVATION:
                    viewReservation();
                    break;
                case MAKE_RESERVATION:
                    makeReservation();
                    break;
                case EDIT_RESERVATION:
                    editReservation();
                    break;
                case CANCEL_RESERVATION:
                    cancelReservation();
                    break;
            }

        } while (option != MenuOption.EXIT);
    }

    private void viewReservation() throws DataAccessException {
        Host host = getHost();
        if (host == null) {
            view.displayStatus(false, "No host found");
        }  else {
            List<Reservation> reservations = reservationService.findReservations(host.getHostId());
            view.displayReservations(reservations);
        }
    }

    private void makeReservation() throws DataAccessException {
        Host host = getHost();
        if (host == null) {
            view.displayStatus(false, "No host found");
            return;
        }

        view.displayReservations(reservationService.findReservations(host.getHostId()));

        Guest guest = getGuest();
        if (guest == null) {
            view.displayStatus(false, "No guest found");
            return;
        }

        Reservation reservation = view.makeReservation(host, guest);

        // Confirm addition of reservation.
        if (confirmReservation(reservation) != null) {
            Result<Reservation> result = reservationService.add(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s created.",
                        result.getPayload().getReservationId());
                view.displayStatus(true, successMessage);
            }
        } else {
            view.printTitle("Cancelling");
        }
    }

    private void editReservation() throws DataAccessException {
        Host host = getHost(); //getHostEmail
        if (host == null) {
            view.displayStatus(false, "No host found");
            return;
        }

        List<Reservation> reservations = reservationService
                .findFutureReservations(host.getHostId());

        Reservation reservation = view.chooseReservation(reservations);
        reservation = view.editReservation(reservation);

        if (view.confirmReservationSummary(reservation)) {
            Result<Reservation> result = reservationService.update(reservation);

            if (result.isSuccess()) {
                view.displayStatus(true, "Reservation " + reservation.getReservationId() + " updated.");
            } else {
                view.displayStatus(false, result.getErrorMessages());
            }
        }
    }

    private void cancelReservation() throws DataAccessException {
        Result<Reservation> result;

        Host host = getHost();
        if (host == null) {
            view.displayStatus(false, "No host found");
            return;
        }

        List<Reservation> reservations = reservationService
                .findFutureReservations(host.getHostId());

        if (reservations.size() == 0) {
            view.displayStatus(false, "No reservations found.");
            return;
        }

        Reservation reservation = view.chooseReservation(reservations);

        result = reservationService.deleteByReservationId(host.getHostId(),
                reservation.getReservationId());

        if (result.isSuccess()) {
            view.displayStatus(true, "Reservation " + reservation.getReservationId() + " cancelled.");
        } else {
            view.displayStatus(false, result.getErrorMessages());
        }
    }

    private Host getHost() {
        System.out.println();
        String hostEmail = view.getEmail("Host");
        return hostService.findByEmail(hostEmail);
    }

    private Guest getGuest() {
        System.out.println();
        String guestEmail = view.getEmail("Guest");
        return guestService.findByEmail(guestEmail);
    }

    private Reservation confirmReservation(Reservation reservation) {
        boolean confirmed = view.confirmReservationSummary(reservation);
        if (confirmed) return reservation;

        return null;
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
