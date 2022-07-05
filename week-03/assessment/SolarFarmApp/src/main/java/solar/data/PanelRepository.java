//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.data;

import solar.models.Panel;
import java.util.List;

//create PanelRepository interface
public interface PanelRepository {
    //find all panels
    List<Panel> findAll() throws DataAccessException;
    //find panels by their id
    Panel findById(int id) throws DataAccessException;
    //find panels by their section
    List<Panel> findBySection(String section) throws DataAccessException;
    //add panels
    Panel add(Panel panel) throws DataAccessException;
    //update panels
    boolean update(Panel panel) throws DataAccessException;
    //remove panels
    boolean deleteById(int id) throws DataAccessException;

}
