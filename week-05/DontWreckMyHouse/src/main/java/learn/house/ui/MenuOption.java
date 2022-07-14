//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;

public enum MenuOption {

        //---Defining the Enum---
    EXIT(0, "Exit"),
    VIEW_RESERVATION(1, "View Reservations"),
    MAKE_RESERVATION(2, "Make Reservations"),
    EDIT_RESERVATION(3, "Edit Reservations"),
    DELETE_RESERVATION(4, "Delete Reservations");
        //---Additional options to be added later for stretch goals---
            //Create/Edit/Delete guests
            //Create/Edit/Delete Hosts
            //ViewReservations for Guests
            //Might modify menu options all together -> "3. Host Menu", "4. Guest Menu"

    private int number;
    private String message;

        //---Constructor---
    MenuOption(int number, String message) {
        this.number = number; //each menu option has a unique number
        this.message = message; //each menu option has a unique message
    }

        //---Menu Option Method---
    public static MenuOption fromValue(int value) {
        for(MenuOption option : MenuOption.values()) {
            if (option.getNumber() == value) {
                return option; //selected option gets returned if it exists
            }
        }
        return EXIT;
    }

        //---getters---
    public int getNumber() { return number; }
    public String getMessage() { return message; }
}
