package learn.house.data;

import learn.house.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

public class GuestFileRepositoryTest {

    static final String SEED_FILE = "./test-data/guests-seed.csv";
    static final String TEST_FILE = "./test-data/guests.csv";

    GuestFileRepository repository = new GuestFileRepository(TEST_FILE);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE);
        Path testPath = Paths.get(TEST_FILE);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldReturnExistingGuest() {
        int guestId = 1;
        Guest guest = repository.findById(guestId);
        int expected = guestId;
        int actual = guest.getGuestId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnMissingGuest() {
        int guestId = -99;
        Guest guest = repository.findById(guestId);
        assertNull(guest);
    }

    @Test
    void shouldNotReturnGuestWithNegativeOfExistingId() {
        int guestId = -1;
        Guest guest = repository.findById(guestId);
        assertNull(guest);
    }

    @Test
    void shouldReturnExistingGuestByEmail() {
        String email = "slomas0@mediafire.com";
        Guest guest = repository.findByEmail(email);
        String expected = email;
        String actual = guest.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnMissingGuestByEmail() {
        String email = "blah@blah.gov";
        Guest guest = repository.findByEmail(email);
        assertNull(guest);
    }

}
