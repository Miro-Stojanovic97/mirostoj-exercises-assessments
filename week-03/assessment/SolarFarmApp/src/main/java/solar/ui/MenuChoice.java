package solar.ui;

public enum MenuChoice {
    //options that the user can choose
    //will be used in Controller.java and View.java to give user options to choose from and select

    EXIT("Exit"),
    DISPLAY_PANELS("Find Panels by Section"),
    CREATE_PANEL("Add a Panel"),
    UPDATE_PANEL("Update a Panel"),
    DELETE_PANEL("Remove a Panel");

    private final String title;

    MenuChoice(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}