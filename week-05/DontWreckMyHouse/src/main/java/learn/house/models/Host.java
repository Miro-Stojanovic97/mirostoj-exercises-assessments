//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.models;

import java.math.BigDecimal;

public class Host {

        //---Host class constructor fields---
    private String hostId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private BigDecimal standardRate;
    private BigDecimal weekendRate;

        //---Constructor representing an existing Host
    public Host(String hostId, String firstName, String lastName, String email, String phone, String address, String city, String state, BigDecimal standardRate, BigDecimal weekendRate) {
        this.hostId = hostId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.standardRate = standardRate;
        this.weekendRate = weekendRate;
    }

    //---Constructor representing a new Host---
    public Host() {
    }

        //---Getters and Setters for Host---
    public String getHostId() { return hostId; }
    public void setHostId(String hostId) { this.hostId = hostId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public BigDecimal getStandardRate() { return standardRate;}
    public void setStandardRate(BigDecimal standardRate) { this.standardRate = standardRate; }

    public BigDecimal getWeekendRate() { return weekendRate;}
    public void setWeekendRate(BigDecimal weekendRate) { this.weekendRate = weekendRate; }

}
