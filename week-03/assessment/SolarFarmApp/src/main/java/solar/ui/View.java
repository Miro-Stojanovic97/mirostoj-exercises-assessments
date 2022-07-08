//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.ui;

import solar.domain.PanelResult;
import solar.models.Material;
import solar.models.Panel;


import java.util.List;
import java.util.Scanner;


public class View {

    private final Scanner console = new Scanner(System.in);

    public MenuChoice displayMenuAndSelect() {
        MenuChoice[] values = MenuChoice.values(); //used MenuOption enum for option values
        System.out.println("----------");
        System.out.println("Main Menu");
        System.out.println("----------");
        //display option titles (Exit, Find, add, update, remove,) 0-4 line by line
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i, values[i].getTitle());
        }

        //prompt user to select option choice using readInt()
        int index = readInt("Select [0-4]: ", 0, 4);
        return values[index]; //return choice
    }


    //display panels and panel details
    public void displayPanels(List<Panel> panels) {
        System.out.println("Panels");
        if (panels.size() == 0) {
            System.out.println("No panels found."); //if no panels have been created yet
        } else {
            //display id, section, row, column, material, year, and tracking status
            for (Panel p : panels) {
                System.out.printf("%s: %s - %s - %s, %s, %s - %s\n",
                        p.getId(),
                        p.getSection(),
                        p.getRow(),
                        p.getColumn(),
                        p.getMaterial(),
                        p.getYearInstalled(),
                        p.isTracking());
            }
        }
    }

    //display result after completed action
    public void displayResult(PanelResult result) {
        if (result.isSuccess()) {
            System.out.println("Success!");
        } else {
            System.out.println("Error: ");
            for (String err : result.getMessages()) {
                System.out.println(err);
            }
        }
    }

    //displaying creating a panel, set details from user
    public Panel makePanel() {
        Panel panel = new Panel();
        panel.setSection(readRequiredString("Section: "));
        panel.setRow(readInt("Row: ", 1, 250));
        panel.setColumn(readInt("Column: ", 1, 250));
        panel.setMaterial(readPanelMaterial());
        panel.setYearInstalled(String.valueOf(readInt("Year Installed: ",
                1950,
                2022)));

        panel.setTracking(readRequiredBoolean("Tracking (y/n)?: "));
        return panel;
    }

    public Panel update(List<Panel> panels) {
        Panel panel = findPanel(panels);
        if (panel != null) {
            update(panel);
        }
        return panel;
    }

    public Panel findPanel(List<Panel> panels) {
        displayPanels(panels);

        if (panels.size() == 0) {
            return null;
        }

        int panelId = readInt("Panel Id: ");
        for (Panel o : panels) {
            if (o.getId() == panelId) {
                return o;
            }
        }

        System.out.println("Panel ID " + panelId + " not found.");
        return null;

    }

    //displaying creating a panel, set details from user
    private Panel update(Panel panel) {
        String section = readString("Section (" + panel.getSection() + "): ");
        if (section.trim().length() > 0) panel.setSection(section);

        int row = readInt("Row (" + panel.getRow() + "): ", 1, 250);
        panel.setRow(row);

        int column = readInt("Column (" + panel.getColumn() + "): ", 1, 250);
        panel.setColumn(column);

        System.out.println("Material ("
                + panel.getMaterial().getName()
                + "): ");
        Material material = readPanelMaterial();
        panel.setMaterial(readPanelMaterial());

        String year = String.valueOf(readInt("Year (" +
                        panel.getYearInstalled() +
                        "): ",
                1950,
                2020));

        panel.setYearInstalled(year);

        boolean tracking = readRequiredBoolean("Tracking ("
                + panel.isTracking()
                + ") (y/n): ");
        panel.setTracking(tracking);

        return panel;
    }

    //show panel materials and prompt user for choice
    public Material readPanelMaterial() {
        System.out.println("Types: ");
        Material[] values = Material.values();
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i, values[i]);
        }
        int index = readInt("Select [0-4]: ", 0, 4);
        return values[index];
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    public String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt).trim(); //trim the string
            if (result.length() == 0) {
                System.out.println("Value is required."); //ensure string was entered
            }
        } while (result.length() == 0);
        return result;
    }

    //check tracking boolean (y/n)
    public boolean readRequiredBoolean(String prompt) {
        boolean valid = false;
        String result = null;
        do {
            result = readString(prompt).trim();
            if (result.equalsIgnoreCase("y") || result.equalsIgnoreCase("n")) {
                valid = true;
            }
        } while (valid == false); //waits until y or n is correctly entered

        return result.equalsIgnoreCase("y");
    }

    //read int for panel id
    private int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;
        do {
            String value = readRequiredString(prompt);
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Value must be an integer.");
            }
        } while (!isValid);
        return result;
    }

    //read int for user choice inputs
    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Value must be an integer between %s and %s.\n",
                        min,
                        max);
            }
        } while (result < min || result > max);
        return result;
    }
}
