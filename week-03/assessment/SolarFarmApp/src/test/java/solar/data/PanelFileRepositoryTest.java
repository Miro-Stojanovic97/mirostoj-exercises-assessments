package solar.data;

import solar.models.Material;
import solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PanelFileRepositoryTest {

    //set paths
    private static final String SEED_PATH = "./data/panels-seed.csv";
    private static final String TEST_PATH = "./data/panels-test.csv";
    private PanelFileRepository repository = new PanelFileRepository(TEST_PATH);

    //before each test, reset the file that's being tested
    @BeforeEach
    void setup() throws IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }
    //test to see if it finds the right number of panels
    @Test
    void shouldFindNinePanels() throws DataAccessException {
        List<Panel> actual = repository.findAll();
        assertNotNull(actual);
        assertEquals(9, actual.size());
    }

    //test to see if it finds an existing panel
    @Test
    void shouldFindExistingPanel() throws DataAccessException {
        Panel fieldPanel = repository.findById(2);
        assertNotNull(fieldPanel);
        assertEquals(Material.POLYSI, fieldPanel.getMaterial());
    }

    //test to see if it returns null for a panel that doesn't exist
    @Test
    void shouldNotFindNonExistentPanel() throws DataAccessException {
        Panel nan = repository.findById(100); //non-existent id
        assertNull(nan);
    }

    //test to see if it finds the right number of panels in section
    @Test
    void shouldFindThreeInEachSection() throws DataAccessException {
        List<Panel> front = repository.findBySection("Front");
        assertNotNull(front);
        assertEquals(3, front.size());

        List<Panel> roof = repository.findBySection("Roof");
        assertNotNull(roof);
        assertEquals(3, roof.size());

        List<Panel> back = repository.findBySection("Back");
        assertNotNull(back);
        assertEquals(3, back.size());
    }

    //test to see if it correctly adds the panel
    @Test
    void shouldAddPanel() throws DataAccessException {
        Panel panel = new Panel();
        panel.setId(10);
        panel.setSection("Roof");
        panel.setRow(1);
        panel.setColumn(4);
        panel.setMaterial(Material.CIGS);
        panel.setYearInstalled("2022");
        panel.setTracking(true);

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertEquals(10, actual.getId());
    }

    //test to see if it correctly updates a panel
    @Test
    void shouldUpdateExistingPanel() throws DataAccessException {
        Panel panel = new Panel();
        panel.setId(1);
        panel.setSection("Front");
        panel.setRow(1);
        panel.setColumn(1);
        panel.setMaterial(Material.CIGS);
        panel.setYearInstalled("2022");
        panel.setTracking(true);

        boolean success = repository.update(panel);
        assertTrue(success);

        Panel actual = repository.findById(1);
        assertNotNull(actual);

        assertEquals("Front", actual.getSection());
        assertEquals(1, actual.getId());
        assertEquals(Material.CIGS, actual.getMaterial());
    }

    //test to see if it returns false trying to update a nonexistent panel
    @Test
    void shouldNotUpdateMissingPanel() throws DataAccessException {
        Panel panel = new Panel();
        panel.setId(747);
        boolean actual = repository.update(panel);
        assertFalse(actual);
    }

    //test to see if it correctly deletes a panel
    @Test
    void shouldDeleteExistingPanel() throws DataAccessException {
        boolean actual = repository.deleteById(2);
        assertTrue(actual);
        Panel p = repository.findById(2);
        assertNull(p);
    }

    //test to see if it correctly returns false trying to delete a nonexistent panel
    @Test
    void shouldNotDeleteMissingPanel() throws DataAccessException {
        boolean actual = repository.deleteById(747);
        assertFalse(actual);
    }

}