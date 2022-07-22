package learn.house.data;

import learn.house.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

public class HostFileRepositoryTest {

    static final String SEED_FILE = "./test-data/hosts-seed.csv";
    static final String TEST_FILE = "./test-data/hosts.csv";

    HostFileRepository repository = new HostFileRepository(TEST_FILE);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE);
        Path testPath = Paths.get(TEST_FILE);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldReturnExistingHost() {
        String hostId = "3edda6bc-ab95-49a8-8962-d50b53f84b15";
        Host host = repository.findByHostId(hostId);
        String expected = hostId;
        String actual = host.getHostId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnMissingHost() {
        String hostId = "blahhhhh";
        Host host = repository.findByHostId(hostId);
        assertNull(host);
    }

    @Test
    void shouldReturnExistingHostByEmail() {
        String hostEmail = "eyearnes0@sfgate.com";
        Host host = repository.findByEmail(hostEmail);
        String expected = hostEmail;
        String actual = host.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnMissingHostByEmail() {
        String hostEmail = "blahh@blahh.com";
        Host host = repository.findByEmail(hostEmail);
        assertNull(host);
    }
}
