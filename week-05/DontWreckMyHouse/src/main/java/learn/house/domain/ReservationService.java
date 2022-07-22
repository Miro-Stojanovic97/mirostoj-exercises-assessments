//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepository;
import learn.house.data.HostRepository;
import learn.house.data.ReservationRepository;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    public List<Reservation> findReservations(String hostId) throws DataAccessException {
        Host host = hostRepository.findByHostId(hostId);
        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(Guest::getGuestId, i -> i));

        List<Reservation> result = reservationRepository.findByHost(hostId);
        for (Reservation r : result) {
            r.setHost(host);
            r.setGuest(guestMap.get(r.getGuestId()));
        }
        return result;
    }

    public List<Reservation> findFutureReservations(String hostId) throws DataAccessException {
        return findReservations(hostId).stream().filter(reservation -> reservation
                        .getStartDate().isAfter(LocalDate.now())).collect(Collectors.toList());

    }

    public Result<Reservation> add(Reservation reservation) throws DataAccessException {

        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        reservation.calculatePriceTotal();

        result.setPayload(reservationRepository.add(reservation));
        return result;
    }

    private Result<Reservation> validate(Reservation reservation)
            throws DataAccessException {

        Result<Reservation> result = new Result<>();

        validateFields(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateDates(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;

    }

    private void validateDates(Reservation reservation,
                               Result<Reservation> result)
            throws DataAccessException {

        List<Reservation> reservations =
                reservationRepository.findByHost(reservation.getHost().getHostId());

        LocalDate reservationStart = reservation.getStartDate();
        LocalDate reservationEnd = reservation.getEndDate();

        if (!reservationStart.isAfter(LocalDate.now())) {
            result.addErrorMessage("Reservation starts in the past.");
        }

        if (reservationStart.isAfter(reservationEnd)) {
            result.addErrorMessage("Reservation ends before start date.");
        }

        for (Reservation r : reservations) {
            LocalDate existingStart = r.getStartDate();
            LocalDate existingEnd = r.getEndDate();

            if (reservation.getReservationId() != r.getReservationId() && isOverlapping(existingStart, existingEnd,
                    reservationStart, reservationEnd)) {

                result.addErrorMessage("Reservation conflicts with " +
                        "existing reservation");
            }
        }
    }

    private void validateFields(Reservation reservation,
                                Result<Reservation> result) {

        if (reservation.getGuest() == null) {
            result.addErrorMessage("Guest cannot be blank");
        }

        if (reservation.getHost() == null) {
            result.addErrorMessage("Host cannot be blank");
        }

        if (reservation.getStartDate() == null) {
            result.addErrorMessage("Start cannot be blank");
        }

        if (reservation.getEndDate() == null) {
            result.addErrorMessage("End cannot be blank");
        }

    }

    public Result<Reservation> update(Reservation reservation)
            throws DataAccessException {

        Result<Reservation> result = validate(reservation);

        if (result.isSuccess()) {
            if (reservationRepository.update(reservation)) {
                result.setPayload(reservation);
            } else {
                result.addErrorMessage("Failed to update reservation.");
            }
        }
        return result;
    }

    public Result<Reservation> deleteByReservationId(String hostId, int id)
            throws DataAccessException {

        Result<Reservation> result = new Result<>();

        Reservation reservation = reservationRepository.findByReservationId(hostId, id);

        // if reservation start is in the past, cannot cancel.
        if (reservation.getStartDate().isBefore(LocalDate.now())) {
            String message =
                    String.format("Reservation %s has already begun.", id);
            result.addErrorMessage(message);
            return result;
        }

        if (!reservationRepository.deleteByReservationId(hostId, id)) {
            String message = String.format("Reservation %s was not found.", id);
            result.addErrorMessage(message);
        }

        return result;
    }

    private boolean isOverlapping(LocalDate start1, LocalDate end1,
                                  LocalDate start2, LocalDate end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

}
