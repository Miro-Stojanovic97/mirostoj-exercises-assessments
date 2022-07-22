//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

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
public class HostFileRepository implements HostRepository {

    private static final String HEADER =
            "id,last_name,email,phone,address,city,state," +
                    "postal_code,standard_rate,weekend_rate";

    private final String filePath;
    public HostFileRepository(@Value("${hostFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 10) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;    }

    @Override
    public Host findByHostId(String id) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields[0].equals(id)) {
                    return deserialize(fields);
                }
            }

        } catch (IOException ex) {

        }

        return null;
    }

    private Host deserialize(String[] fields) {
        Host result = new Host();

        result.setHostId(fields[0]);
        result.setLastName(fields[1]);
        result.setEmail(fields[2]);
        result.setPhone(fields[3]);
        result.setAddress(fields[4]);
        result.setCity(fields[5]);
        result.setState(fields[6]);
        result.setStandardRate(new BigDecimal(fields[8]));
        result.setWeekendRate(new BigDecimal(fields[9]));

        return result;
    }

    @Override
    public Host findByEmail(String email) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields[2].equals(email)) {
                    return deserialize(fields);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return null;
    }
}