//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;


//imports for spring annotations
import learn.house.data.DataAccessException;
import learn.house.domain.GuestService;
import learn.house.domain.HostService;
import learn.house.domain.ReservationService;
import learn.house.models.Host;
import learn.house.models.Reservation;
import learn.house.ui.View;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
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
        view.printTitle("Welcome to Don't Wreck My House!");

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
                    //makeReservation();
                    break;
                case EDIT_RESERVATION:
                    //editReservation();
                    break;
                case CANCEL_RESERVATION:
                    //cancelReservation();
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

    private Host getHost() {
        String hostEmail = view.getEmail("Host");
        return hostService.findByEmail(hostEmail);
    }
}
