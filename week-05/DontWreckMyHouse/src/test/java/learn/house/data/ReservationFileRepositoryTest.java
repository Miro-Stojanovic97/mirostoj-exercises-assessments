package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {

    static final String SEED_DIR = "./test-data/reservations-seed";
    static final String TEST_DIR = "./test-data/reservations";

    ReservationFileRepository repository =
            new ReservationFileRepository(TEST_DIR);

    @BeforeEach
    void setup() throws IOException {
        File seedPath = new File(SEED_DIR);
        File testPath = new File(TEST_DIR);
        FileUtils.deleteDirectory(testPath);
        FileUtils.copyDirectory(seedPath, testPath);
    }

    //begin tests
    @Test
    void shouldFindReservationsByHostId() throws DataAccessException {
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        List<Reservation> reservations = repository.findByHost(hostId);
        int expected = 12;
        int actual = reservations.size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindReservationsByNonExistentHostId() throws DataAccessException {
        String hostId = "NOT_A_HOST";
        List<Reservation> reservations = repository.findByHost(hostId);
        int expected = 0;
        int actual = reservations.size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindReservationByHostAndId() throws DataAccessException {
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        int reservationId = 1;
        Reservation reservation = repository.findByReservationId(hostId, reservationId);
        int expected = 663;
        int actual = reservation.getGuestId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissingReservation() throws DataAccessException {
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        int reservationId = 100;
        Reservation reservation = repository.findByReservationId(hostId, reservationId);
        assertNull(reservation);
    }

    @Test
    void shouldAddReservation() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setHostId("TEST_HOST_ID");
        Guest guest = new Guest();
        guest.setGuestId(2);
        reservation.setHost(host);
        reservation.setHostId(host.getHostId());
        reservation.setStartDate(LocalDate.of(2022, 1, 1));
        reservation.setEndDate(LocalDate.of(2022, 1, 7));
        reservation.setGuest(guest);
        reservation.setGuestId(guest.getGuestId());
        reservation.setPriceTotal(new BigDecimal("100.00"));

        reservation = repository.add(reservation);

        assertEquals(1, reservation.getReservationId());
    }

    @Test
    void shouldUpdateExistingReservation() throws DataAccessException {
        Host host = new Host();
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        host.setHostId(hostId);
        Guest guest = new Guest();
        int guestId = 850;
        guest.setGuestId(guestId);
        int reservationId = 7;

        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setReservationId(reservationId);
        reservation.setStartDate(LocalDate.of(2022, 05, 15));
        reservation.setEndDate(LocalDate.of(2022, 05, 21));
        reservation.setPriceTotal(new BigDecimal("125.00"));

        assertTrue(repository.update(reservation));

        LocalDate expected = LocalDate.of(2022, 05, 21);
        LocalDate actual = reservation.getEndDate();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissingReservation() throws DataAccessException {
        Host host = new Host();
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        host.setHostId(hostId);
        Guest guest = new Guest();
        int guestId = 850;
        guest.setGuestId(guestId);
        int reservationId = 999;

        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setReservationId(reservationId);
        reservation.setStartDate(LocalDate.of(2022, 05, 15));
        reservation.setEndDate(LocalDate.of(2022, 05, 21));
        reservation.setPriceTotal(new BigDecimal("125.00"));

        assertFalse(repository.update(reservation));
    }

    @Test
    void shouldDeleteExistingReservation() throws DataAccessException {
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        int reservationId = 1;

        assertTrue(repository.deleteByReservationId(hostId, reservationId));

    }

    @Test
    void shouldNotDeleteMissingReservation() throws DataAccessException {
        String hostId = "2e72f86c-b8fe-4265-b4f1-304dea8762db";
        int reservationId = 999;

        assertFalse(repository.deleteByReservationId(hostId, reservationId));

    }
}