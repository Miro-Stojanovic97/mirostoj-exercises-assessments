public class Hotel {
    private String name;
    private Guest[] guests;
    private Room[] rooms;
    private int hotelCapacity;

    public int getHotelCapacity() {
        return hotelCapacity;
    }

    public Hotel(String hotelName, int hotelCapacity) {
        this.hotelCapacity = hotelCapacity;
        this.name = hotelName;
    }

    public Guest[] getGuests() {
        return guests;
    }

    public void setGuests(Guest[] guests) {
        this.guests = guests;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
}