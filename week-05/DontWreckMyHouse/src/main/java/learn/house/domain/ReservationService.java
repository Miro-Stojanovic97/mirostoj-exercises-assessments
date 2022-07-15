//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepository;
import learn.house.data.HostRepository;
import learn.house.data.ReservationRepository;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    public List<Reservation> findReservations(String hostId) throws DataAccessException {
        Host host = hostRepository.findByHostId(hostId);
        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getGuestId(), i -> i));

        List<Reservation> result = reservationRepository.findByHost(hostId);
        for (Reservation r : result) {
            r.setHost(host);
            r.setGuest(guestMap.get(r.getGuestId()));
        }
        return result;
    }



}
