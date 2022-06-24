public class Room {
    // room #
    private int roomNumber;
    private boolean isOccupied;
    private Guest guest;

    public int getRoomNumber() {
        return roomNumber;
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}