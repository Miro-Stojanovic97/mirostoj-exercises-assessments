//Miro Stojanovic
//Assessment 3, Solar Panel App

package solar.models;

public class Panel {

    //panel details
    private String section;
    private int row;
    private int column;
    private int id;
    private String yearInstalled;
    private Material material;
    private boolean isTracking;

    public Panel() {

    }

    //panel constructor
    public Panel(int id, String section, int row, int column, Material material,
                 String yearInstalled, boolean isTracking) {

        this.section = section;
        this.row = row;
        this.column = column;
        this.id = id;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.isTracking = isTracking;
    }

    //panel getters and setters
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(String yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }
}
