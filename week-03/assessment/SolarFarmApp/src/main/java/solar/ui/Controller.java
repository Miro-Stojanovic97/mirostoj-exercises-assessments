//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.ui;

import solar.data.DataAccessException;
import solar.domain.PanelResult;
import solar.domain.PanelService;
import solar.models.Panel;
import java.util.List;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    } //controller constructor for use in app.java

    //run solar panel app menu, catch exception
    public void run() {
        try {
            runMenu();
        } catch (DataAccessException ex) {
            System.out.println("Error: " + ex);
        }
    }

    //runMenu() method, displays and has user choose option
    private void runMenu() throws DataAccessException {
        MenuChoice option;
        do {
            option = view.displayMenuAndSelect();
            switch(option) {
                case EXIT:
                    System.out.println("Exiting App... Bye!");
                    break;
                case DISPLAY_PANELS:
                    displayPanels();
                    break;
                case CREATE_PANEL:
                    createPanel();
                    break;
                case UPDATE_PANEL:
                    updatePanel();
                    break;
                case DELETE_PANEL:
                    deletePanel();
                    break;
            }
        } while (option != MenuChoice.EXIT); //stop running once exit is chosen
    }

    //Display panel
    private void displayPanels() throws DataAccessException {
        System.out.println(MenuChoice.DISPLAY_PANELS.getTitle());

        String section = view.readRequiredString("Section: ");
        List<Panel> panels = service.findBySection(section);

        view.displayPanels(panels);
    }

    //Make panel
    private void createPanel() throws DataAccessException {
        System.out.println(MenuChoice.CREATE_PANEL.getTitle());

        Panel panel = view.makePanel();
        PanelResult result = service.add(panel);

        view.displayResult(result);
    }

    //Update panel
    private void updatePanel() throws DataAccessException {
        System.out.println(MenuChoice.UPDATE_PANEL.getTitle());

        String section = view.readRequiredString("Section: ");
        List<Panel> panels = service.findBySection(section);

        Panel panel = view.update(panels);
        if (panel == null) {
            return;
        }

        PanelResult result = service.update(panel);
        view.displayResult(result);
    }

    //delete panel
    private void deletePanel() throws DataAccessException {
        System.out.println(MenuChoice.DELETE_PANEL.getTitle());

        String section = view.readRequiredString("Section: ");
        List<Panel> panels = service.findBySection(section);

        Panel panel = view.findPanel(panels);
        if (panel == null) {
            return;
        }

        PanelResult result = service.deleteById(panel.getId()); //delete
        view.displayResult(result);

    }
}
