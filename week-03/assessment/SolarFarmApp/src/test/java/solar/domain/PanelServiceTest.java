package solar.domain;

import solar.data.DataAccessException;
import solar.data.PanelRepositoryDouble;
import solar.models.Material;
import solar.models.Panel;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    PanelService service = new PanelService(new PanelRepositoryDouble());

    //test to see if it finds sections correctly
    @Test
    void shouldFindBySection() throws DataAccessException {
        List<Panel> backPanels = service.findBySection("Back");
        assertEquals(2, backPanels.size());
    }

    @Test
    void shouldNotFindMissingSection() throws DataAccessException {
        List<Panel> bayPanels = service.findBySection("DNE");
        assertEquals(0, bayPanels.size());
    }

    //test to see if it correctly adds at empty panel locations
    @Test
    void shouldAddAtEmptyLocation() throws DataAccessException {
        PanelResult result = service.add(new Panel(
                7, "Front",
                1, 3,
                Material.POLYSI,
                "2021",
                true));
        assertTrue(result.isSuccess());
    }

    //test to see if it correctly does not add at existing panel locations
    @Test
    void shouldNotAddAtOccupiedLocation() throws DataAccessException {
        PanelResult result = service.add(new Panel(
                7, "Front",
                1, 1,
                Material.POLYSI,
                "2020",
                true));
        assertFalse(result.isSuccess());
    }

    //test to see if it correctly does not add null panels
    @Test
    void shouldNotAddNullPanel() throws DataAccessException {
        PanelResult result = service.add(null);
        assertFalse(result.isSuccess());
    }

    //test to see if it correctly deletes panel
    @Test
    void shouldDelete() throws DataAccessException {
        PanelResult result = service.deleteById(1);
        assertTrue(result.isSuccess());
    }

    //test to see if it correctly updates panel
    @Test
    void shouldUpdate() throws DataAccessException {
        PanelResult result = service.update(new Panel(
                1, "Front",
                1, 1,
                Material.POLYSI,
                "2015",
                true));
        assertTrue(result.isSuccess());
    }

    //test to see if it correctly does not update panel section - Null
    @Test
    void shouldNotUpdateNullSection() throws DataAccessException {
        PanelResult result = service.update(new Panel(
                1, null,
                1,1,
                Material.POLYSI,
                "2019",
                true));
        assertFalse(result.isSuccess());
    }

    //test to see if it correctly does not update panel section - Empty
    @Test
    void shouldNotUpdateEmptySection() throws DataAccessException {
        PanelResult result = service.update(new Panel(1, "   ",
                1,2,
                Material.POLYSI,
                "2012",
                true));
        assertFalse(result.isSuccess());
    }

    //test to see if it correctly does not update panel - Future year
    @Test
    void shouldNotUpdateFutureYear() throws DataAccessException {
        PanelResult result = service.update(new Panel(
                1, "Test Section",
                1,2,
                Material.POLYSI,
                "2030",
                true));
        assertFalse(result.isSuccess());
    }

    //test to see if it correctly does not update panel - Column out of bounds >250
    @Test
    void shouldNotUpdateLocationOutOfBounds() throws DataAccessException {
        PanelResult result = service.update(new Panel(
                1, "Test Location",
                1,251,
                Material.POLYSI,
                "2019",
                true));
        assertFalse(result.isSuccess());
    }
}
