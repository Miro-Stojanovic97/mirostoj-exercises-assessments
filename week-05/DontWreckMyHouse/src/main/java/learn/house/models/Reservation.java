//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.models;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

        //---Reservation class constructor fields---
    private int reservationId;
    private Host host;
    private Guest guest;
    private String hostId;
    private int guestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal priceTotal;

        //---Constructor representing an existing Reservation
    public Reservation(int reservationId, Host host, Guest guest, String hostId, int guestId, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.host = host;
        this.guest = guest;
        this.hostId = hostId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceTotal = priceTotal;
    }

        //---Constructor representing a new Reservation---
    public Reservation() {
    }

        //---Getters and Setters for Reservation---
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public Host getHost() { return host; }
    public void setHost(Host host) { this.host = host; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public String getHostId() { return hostId; }
    public void setHostId(String hostId) { this.hostId = hostId; }

    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BigDecimal getPriceTotal() { return priceTotal; }
    public void setPriceTotal(BigDecimal total) { this.priceTotal = total; }

        //---further methods: calculating price total---
    public void calculatePriceTotal() {
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate); //total days (all) in the reservation
        long weekendDays = 0; //total weekend days in the reservation

        for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1)) {
            //if there's a weekend day in the reservation, tally it++
            if(d.getDayOfWeek() == DayOfWeek.FRIDAY || d.getDayOfWeek() == DayOfWeek.SATURDAY) {
                weekendDays++;
            }
        }
        //standard price total = weekdays * weekday rate
        BigDecimal standardPriceTotal = host.getStandardRate().multiply(BigDecimal.valueOf(totalDays - weekendDays));

        //weekend price total = weekend days * weekend rate
        BigDecimal weekendPriceTotal = host.getWeekendRate().multiply(BigDecimal.valueOf(weekendDays));

        //reservation price total = standard price total + weekend price total
        setPriceTotal(standardPriceTotal.add(weekendPriceTotal));
    }

}
