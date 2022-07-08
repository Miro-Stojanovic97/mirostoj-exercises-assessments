//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.domain;

import solar.data.DataAccessException;
import solar.data.PanelRepository;
import solar.models.Panel;
import java.util.List;


public class PanelService {

    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findBySection(String section)
            throws DataAccessException {

        return repository.findBySection(section);
    }

    public PanelResult add(Panel panel) throws DataAccessException {
        PanelResult result = validateInputs(panel);
        if(!result.isSuccess()){
            return result;
        }

        result = validateNoDuplicates(panel);
        if(!result.isSuccess()){
            return result;
        }

        Panel p = repository.add(panel);
        result.setPanel(p);
        return result;
    }

    private PanelResult validateNoDuplicates(Panel panel)
            throws DataAccessException {

        PanelResult result = new PanelResult();
        for (Panel p : repository.findAll()) {
            if (p.getSection().equalsIgnoreCase(panel.getSection())
                    && p.getColumn() == panel.getColumn()
                    && p.getRow() == panel.getRow()) {

                result.addErrorMessage("A panel already exists here.");
            }
        }
        return result;
    }

    public PanelResult validateInputs(Panel panel) {
        PanelResult result = new PanelResult();
        if(panel == null) {
            result.addErrorMessage("Panel cannot be null"); ///!
            return result;
        }

        if(panel.getSection() == null
                || panel.getSection().trim().equalsIgnoreCase("")) {

            result.addErrorMessage("A section is required"); ///!
            return result;
        }

        if(panel.getColumn() < 1 || panel.getColumn() > 250) {
            result.addErrorMessage("Column must be between 1 and 250"); ///!
            return result;
        }

        if(panel.getRow() < 1 || panel.getRow() > 250) {
            result.addErrorMessage("Row must be between 1 and 250"); ///!
            return result;
        }

        if(Integer.parseInt(panel.getYearInstalled()) < 1950 ||
                Integer.parseInt(panel.getYearInstalled()) > 2022) {
            result.addErrorMessage("Year must be between 1950 and 2022"); ///!
            return result;
        }

        return result;
    }

    public PanelResult update(Panel panel) throws DataAccessException {
        PanelResult result = validateInputs(panel);
        if(panel == null) {
            result.addErrorMessage("Panel cannot be null");
            return result;
        }
        Panel existing = repository.findById(panel.getId());
        if (existing == null) {
            result.addErrorMessage("Panel Id "
                    + panel.getId()
                    + " not found.");
            return result;
        }

        boolean success = repository.update(panel);
        if(!success) {
            result.addErrorMessage("Could not find ID " + panel.getId());
        }
        return result;
    }

    public PanelResult deleteById(int id) throws DataAccessException {
        PanelResult result = new PanelResult();
        Panel panel = repository.findById(id);
        if(panel == null) {
            result.addErrorMessage("The panel ID does not exist "
                    + panel.getId());
            return result;
        }

        boolean success = repository.deleteById(id);
        if (!success) {
            result.addErrorMessage("The panel ID does not exist "
                    + panel.getId());
        }
        return result;
    }
}
