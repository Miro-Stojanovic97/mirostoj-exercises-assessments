package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReservationRepositoryDouble implements ReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();
    public Host host;
    public Guest guest;
    public Guest guest2;
    public Guest guest3;

    public  ReservationRepositoryDouble() {
        host = new Host("TEST_HOST_ID", "Miro", "Stojanovic", "mir32@gmail.com", "414-444-3333",
                "1111 W Wisconsin Ave", "Milwaukee", "WI", new BigDecimal("300"), new BigDecimal("700"));

        guest = new Guest(1, "Mike", "Mikalski", "mikii77@gmail.com", "414-888-9999");
        guest2 = new Guest(2, "Mikey", "Mikaloultra", "mik44@gmail.com", "414-889-5454");
        guest3 = new Guest(3, "Mikael", "Mikalobyebye", "mi987@gmail.com", "414-845-2323");

        LocalDate startDate = LocalDate.of(2023, 7, 10);
        LocalDate endDate = LocalDate.of(2023, 7, 20);

        reservations.add(new Reservation(1, host, guest, host.getHostId(), guest.getGuestId(), startDate, endDate));
        reservations.add(new Reservation(2, host, guest2, host.getHostId(), guest2.getGuestId(), startDate.plusDays(14), endDate.plusDays(14)));
        reservations.add(new Reservation(3, host, guest3, host.getHostId(), guest3.getGuestId(), startDate.minusYears(10), endDate.minusYears(10)));

    }

    @Override
    public List<Reservation> findAll() { return reservations; }

    @Override
    public List<Reservation> findByHost(Host host) { return reservations; }

    @Override
    public List<Reservation> findByHost(String hostId) throws DataAccessException {
        return reservations;
    }

    @Override
    public Reservation findByReservationId(Host host, int reservationId) {
        return null;
    }

    public Reservation findByReservationId(String hostId, int reservationId) throws DataAccessException {
        return reservations.stream().filter(reservation -> reservation.getReservationId() == reservationId)
                .findFirst().get();
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        reservation.calculatePriceTotal();
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        for (int i = 0; i < reservations.size(); i++) {
            if(reservation.getReservationId() == reservations.get(i).getReservationId()) {
                reservations.set(i, reservation);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByReservationId(Host host, int reservationId) {
        reservations.removeIf(r -> r.getReservationId() == reservationId);
        return true;
    }

    @Override
    public boolean deleteByReservationId(String hostId, int reservationId) throws DataAccessException {
        reservations.removeIf(r -> r.getReservationId() == reservationId);
        return true;
    }

}
