//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.data;

import solar.models.Material;
import solar.models.Panel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PanelFileRepository implements PanelRepository {

    private final String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<Panel>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // reads header line and ignores it.
            for (
                    String line = reader.readLine();
                    line != null;
                    line = reader.readLine()) {

                //sets panel detail values based on line.split array->  0-ID, 1-Section, 2-Row, 3-Col, 4-Matl, 5-Yr, 6-track
                String[] fields = line.split(",", -1);
                // id,section,row,column,material,yearInst,isTracking
                if (fields.length == 7) {
                    Panel panel = new Panel();
                    panel.setId(Integer.parseInt(fields[0]));
                    panel.setSection(fields[1]);
                    panel.setRow(Integer.parseInt(fields[2]));
                    panel.setColumn(Integer.parseInt(fields[3]));
                    panel.setMaterial(Material.valueOf(fields[4]));
                    panel.setYearInstalled(fields[5]);
                    panel.setTracking(Boolean.valueOf(fields[6]));
                    result.add(panel);
                }
            }
        } catch (FileNotFoundException ex) {
            // OK TO IGNORE
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public Panel findById(int id) throws DataAccessException {
        for (Panel panel : findAll()) {
            if (panel.getId() == id) {
                return panel;
            }
        }
        return null;
    }

    @Override
    public List<Panel> findBySection(String section)
            throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<Panel>();
        for (Panel panel : findAll()) {
            if (panel.getSection().equalsIgnoreCase(section)) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        int nextId = 0;
        for(Panel p : all) {
            nextId = Math.max(nextId, p.getId());
        }
        nextId++;
        panel.setId(nextId); //set next id
        all.add(panel);
        writeAll(all);
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == panel.getId()) {
                all.set(i, panel);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    private void writeAll(List<Panel> panels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("id,section,row,col,material,year,tracking");
            for (Panel p : panels) {
                writer.println(serialize(p));
            }

        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Panel panel) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                panel.getId(),
                panel.getSection(),
                panel.getRow(),
                panel.getColumn(),
                panel.getMaterial(),
                panel.getYearInstalled(),
                panel.isTracking());
    }
}