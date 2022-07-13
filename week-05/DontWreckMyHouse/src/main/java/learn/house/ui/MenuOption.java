package learn.house.ui;

public enum MenuOption {

        //---Defining the Enum---
    EXIT(0, "Exit"),
    VIEW_RESERVATION(1, "View Reservations"),
    MAKE_RESERVATION(2, "Make Reservations"),
    EDIT_RESERVATION(3, "Edit Reservations"),
    DELETE_RESERVATION(4, "Delete Reservations");

    private int number;
    private String message;

        //---Constructor---
    MenuOption(int number, String message) {
        this.number = number; //each menu option has a unique number
        this.message = message; //each menu option has a unique message
    }

        //---getters---
    public int getNumber() { return number; }
    public String getMessage() { return message; }
}
