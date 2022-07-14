//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.models;

public class Host {

        //---Constructing a Host class---
    private int hostId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String state;

        //---Constructor representing an existing Host
    public Host(int hostId, String firstName, String lastName, String email, String phone, String city, String state) {
        this.hostId = hostId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.state = state;
    }

    //---Constructor representing a new Host---
    public Host() {
    }

    //---Getters and Setters for Host---
    public int getHostId() { return hostId; }

    public void setHostId(int hostId) { this.hostId = hostId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
