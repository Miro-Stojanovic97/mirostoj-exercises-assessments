//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationFileRepository implements ReservationRepository {

    private static final String HEADER =
            "id,start_date,end_date,guest_id,total";

    private final String directory;

    public ReservationFileRepository(@Value("${reservationFilePath}") String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findByHost(Host host) {
        return null;
    }

    @Override
    public List<Reservation> findByHost(String hostId) throws DataAccessException {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(getFilePath(hostId)))) {

            reader.readLine();

            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields, hostId));
                }
            }

        } catch (IOException ex) {

        }
        return result;
    }

    @Override
    public Reservation findByReservationId(Host host, int id) {
        return null;
    }

    @Override
    public Reservation findByReservationId(String hostId, int id) throws DataAccessException {
        List<Reservation> reservations = findByHost(hostId);
        for (Reservation r : reservations) {
            if (r.getReservationId() == id) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Reservation add(Reservation reservation)
            throws DataAccessException {

        List<Reservation> reservations
                = findByHost(reservation.getHost().getHostId());

        int nextId = reservations.stream()
                .mapToInt(Reservation::getReservationId)
                .max()
                .orElse(0) + 1;

        reservation.setReservationId(nextId);

        reservations.add(reservation);
        writeAll(reservations, reservation.getHostId());

        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        if (reservation == null) return false;
        List<Reservation> reservations
                = findByHost(reservation.getHost().getHostId());

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId() == reservation.getReservationId()) {
                reservations.set(i, reservation);
                writeAll(reservations, reservation.getHost().getHostId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByReservationId(Host host, int id) {
        return false;
    }

    @Override
    public boolean deleteByReservationId(String hostId, int id) throws DataAccessException {
        List<Reservation> reservations
                = findByHost(hostId);

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId() == id) {
                reservations.remove(i);
                writeAll(reservations, hostId);
                return true;
            }
        }
        return false;
    }

    private void writeAll(List<Reservation> reservations, String hostId)
            throws DataAccessException {

        try (PrintWriter writer = new PrintWriter(getFilePath(hostId))) {

            writer.println(HEADER);

            for (Reservation r : reservations) {
                writer.println(serialize(r));
            }
        } catch (FileNotFoundException ex) {
            throw new DataAccessException(ex);
        }
    }

    private String getFilePath(String hostId) {
        return Paths.get(directory, hostId + ".csv").toString();
    }

    private String serialize(Reservation reservation) {
        return String.format("%s,%s,%s,%s,%s",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuestId(),
                reservation.getPriceTotal());
    }

    private Reservation deserialize(String[] fields, String hostId) {
        Reservation result = new Reservation();

        result.setReservationId(Integer.parseInt(fields[0]));

        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));

        result.setGuestId(Integer.parseInt(fields[3]));

        result.setPriceTotal(new BigDecimal(fields[4]));

        return result;
    }
}