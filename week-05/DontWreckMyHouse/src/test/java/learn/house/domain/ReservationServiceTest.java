package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepositoryDouble;
import learn.house.data.HostRepositoryDouble;
import learn.house.data.ReservationRepositoryDouble;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {

    private static final String HOST_ID = "TEST_HOST_ID";

    ReservationService service;

    Host host = new Host(HOST_ID, "Miro", "Stojanovic", "mir32@gmail.com", "414-777-4444",
            "1111 W Wisconsin Ave", "Milwaukee", "WI", new BigDecimal("100.00"), new BigDecimal("200.00"));

    @BeforeEach
    void setup() {
        service = new ReservationService(new ReservationRepositoryDouble(), new HostRepositoryDouble(), new GuestRepositoryDouble());
    }


    @Test
    void shouldFindThreeReservations() throws DataAccessException {
        List<Reservation> reservations = service.findReservations(HOST_ID);
        assertEquals(3, reservations.size());
    }

    @Test
    void shouldAddReservation() throws DataAccessException {
        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 7, 10), LocalDate.of(2023,7,20));

        Result<Reservation> result = service.add(reservation);

        assertTrue(result.isSuccess());
        assertEquals(4, service.findReservations(host.getHostId()).size());
    }

    @Test
    void shouldNotAddReservationWithFieldsMissing() throws DataAccessException {

        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2023, 8, 10));
        reservation.setEndDate(LocalDate.of(2023, 8, 20));

        Result<Reservation> result = service.add(reservation);

        assertFalse(result.isSuccess());
        assertEquals(3, service.findReservations(host.getHostId()).size());

    }

    @Test
    void shouldNotAddConflictingStartReservation() throws DataAccessException {

        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 7, 15), LocalDate.of(2023,7,25));

        Result<Reservation> result = service.add(reservation);

        assertFalse(result.isSuccess());
        assertEquals(3, service.findReservations(host.getHostId()).size());

    }

    @Test
    void shouldNotAddConflictingMiddleReservation() throws DataAccessException {

        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 7, 12), LocalDate.of(2023, 7, 29));

        Result<Reservation> result = service.add(reservation);

        assertFalse(result.isSuccess());
        assertEquals(3, service.findReservations(host.getHostId()).size());

    }

    @Test
    void shouldNotAddConflictingEndReservation() throws DataAccessException {

        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 7, 26), LocalDate.of(2023, 7, 28));

        Result<Reservation> result = service.add(reservation);

        assertFalse(result.isSuccess());
        assertEquals(3, service.findReservations(host.getHostId()).size());

    }

    @Test
    void twoWeeknightsShouldCalculateCorrectly() throws DataAccessException {

        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 9, 25), LocalDate.of(2023, 9, 27));

        Result<Reservation> result = service.add(reservation);

        assertTrue(result.isSuccess());
        assertEquals(new BigDecimal("200.00"), reservation.getPriceTotal());

    }

    @Test
    void twoWeeknightsAndWeekendShouldCalculateCorrectly() throws DataAccessException {
        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 9, 27), LocalDate.of(2023, 9, 30));

        Result<Reservation> result = service.add(reservation);

        assertTrue(result.isSuccess());
        assertEquals(new BigDecimal("400.00"), reservation.getPriceTotal());
    }

    @Test
    void twoWeekendsAndWeekdayShouldCalculateCorrectly() throws DataAccessException {
        Reservation reservation = new Reservation(1, host, new Guest(), host.getHostId(), 100,
                LocalDate.of(2023, 9, 22), LocalDate.of(2023, 9, 24));

        Result<Reservation> result = service.add(reservation);

        assertTrue(result.isSuccess());
        assertEquals(new BigDecimal("400.00"), reservation.getPriceTotal());
    }

    @Test
    void shouldUpdateExistingReservation() throws DataAccessException {
        List<Reservation> reservations = service.findReservations("TEST_HOST_ID");
        Reservation reservation = reservations.get(0);

        reservation.setHost(new Host());

        reservation.setStartDate(LocalDate.of(2022, 8, 5));
        reservation.setEndDate(LocalDate.of(2022, 8, 15));

        Result<Reservation> result = service.update(reservation);

        LocalDate expected = LocalDate.of(2022, 8, 15);

        assertTrue(result.isSuccess());
        assertEquals(expected, service.findReservations("TEST_HOST_ID").get(0).getEndDate());
    }

    @Test
    void shouldNotUpdateReservationInPast() throws DataAccessException {
        List<Reservation> reservations = service.findReservations("TEST_HOST_ID");
        Reservation reservation = reservations.get(0);

        reservation.setHost(new Host());
        reservation.setStartDate(LocalDate.of(2021, 1, 5));
        reservation.setEndDate(LocalDate.of(2021, 1, 15));

        Result<Reservation> result = service.update(reservation);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateReservationToConflict() throws DataAccessException {
        List<Reservation> reservations = service.findReservations("TEST_HOST_ID");
        Reservation reservation = reservations.get(2);

        reservation.setHost(new Host());
        reservation.setStartDate(LocalDate.of(2021, 1, 10));
        reservation.setEndDate(LocalDate.of(2021, 1, 14));

        Result<Reservation> result = service.update(reservation);

        assertFalse(result.isSuccess());
    }


    @Test
    void shouldFutureReservationDeleteById() throws DataAccessException {
        String hostId = "TEST_HOST_ID";
        int reservationId = 1;
        Result<Reservation> result = service.deleteByReservationId(hostId, reservationId);

        assertTrue(result.isSuccess());
        assertEquals(2, service.findReservations(hostId).size());
    }

    @Test
    void shouldNotDeletePastReservationById() throws DataAccessException {
        String hostId = "TEST_HOST_ID";
        int reservationId = 3;
        Result<Reservation> result = service.deleteByReservationId(hostId, reservationId);

        assertFalse(result.isSuccess());
        assertEquals(3, service.findReservations(hostId).size());
    }
}




