package solar.data;

import solar.models.Material;
import solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelRepositoryDouble implements PanelRepository {

    private ArrayList<Panel> panels = new ArrayList<>();

    public PanelRepositoryDouble() {
        panels.add(new Panel(1,"Front",1,1,Material.POLYSI,"2022",true));
        panels.add(new Panel(2,"Front",1,2,Material.POLYSI,"2022",true));
        panels.add(new Panel(3,"Roof",1,1,Material.POLYSI,"2022",true));
        panels.add(new Panel(4,"Roof",1,2,Material.POLYSI,"2022",true));
        panels.add(new Panel(5,"Back",1,1,Material.POLYSI,"2022",true));
        panels.add(new Panel(6,"Back",1,2,Material.POLYSI,"2022",true));
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return new ArrayList<>(panels);
    }

    @Override
    public Panel findById(int id) throws DataAccessException {
        for (Panel p : panels) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Panel> findBySection(String section)
            throws DataAccessException {

        ArrayList<Panel> result = new ArrayList<>();
        for (Panel p : panels) {
            if (p.getSection().equals(section)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        panels.add(panel);
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return true;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return true;
    }
}