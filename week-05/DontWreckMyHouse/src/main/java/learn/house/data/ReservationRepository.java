//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

import learn.house.models.Host;
import learn.house.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();

    List<Reservation> findByHost(Host host);

    List<Reservation> findByHost(String hostId) throws DataAccessException;

    Reservation findByReservationId(Host host, int reservationId);

    Reservation findByReservationId(String hostId, int reservationId) throws DataAccessException;

    Reservation add(Reservation reservation) throws DataAccessException;

    boolean update(Reservation reservation) throws DataAccessException;

    boolean deleteByReservationId(Host host, int reservationId);

    boolean deleteByReservationId(String hostId, int reservationId) throws DataAccessException;

}
