//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestFileRepository implements GuestRepository {

    private static final String HEADER =
            "guest_id,first_name,last_name,email,phone,state";

    private final String filePath;

    public GuestFileRepository(@Value("${guestFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 6) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Guest findById(int id) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (Integer.parseInt(fields[0]) == id) {
                    return deserialize(fields);
                }
            }

        } catch (IOException ex) {

        }

        return null;
    }

    private Guest deserialize(String[] fields) {
        Guest result = new Guest();

        result.setGuestId(Integer.parseInt(fields[0]));
        result.setFirstName(fields[1]);
        result.setLastName(fields[2]);
        result.setEmail(fields[3]);
        result.setPhone(fields[4]);

        return result;
    }

    @Override
    public Guest findByEmail(String email) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields[3].equalsIgnoreCase(email)) {
                    return deserialize(fields);
                }
            }

        } catch (IOException ex) {

        }

        return null;
    }
}