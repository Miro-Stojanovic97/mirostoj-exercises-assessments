//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar;

import solar.data.PanelFileRepository;
import solar.domain.PanelService;
import solar.ui.Controller;
import solar.ui.View;


public class App {

    public static void main(String[] args) {

        View view = new View();
        PanelFileRepository repository = new PanelFileRepository("./data/panels-seed.csv");
        PanelService service = new PanelService(repository);
        Controller controller = new Controller(service, view);
        controller.run(); //call run() in controller.java
    }
}