package pets.data;


import com.mysql.cj.xdevapi.PreparableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pets.domain.Result;
import pets.models.Pet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@Profile("jdbc")
public class PetJdbcRepository implements PetRepository {

    @Autowired
    private DataSource dataSource;


    @Override
    public List<Pet> findAll() {
        ArrayList<Pet> result = new ArrayList<>();
        final String sql = "select *  from pet;";
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {
            while(rs.next()) {
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setName(rs.getString("name"));
                pet.setType(rs.getString("type"));
                result.add(pet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); //for now, can log later
        }
        return result;
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "Select * from pet where pet_id = ?;";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setInt(1, petId);

            try(ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(rs.getInt("pet_id"));
                    pet.setName(rs.getString("name"));
                    pet.setType(rs.getString("type"));
                    return pet;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Pet pet) {
        final String sql = "update pet set"
                + "name = ?,"
                + "type = ?"
                + "where pet_id = ?;";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getPetId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean deleteById(int petId) {
        final String sql = "delete from pet where pet_id = ?;";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setInt(1, petId);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet(`name`, `type`) value (?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            int rowInserted = statement.executeUpdate();

            if(rowInserted <= 0) {
                return null;
            }
            try(ResultSet keys = statement.getGeneratedKeys()) {
                if(keys.next()) {
                    pet.setPetId(keys.getInt(1));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pet;
    }
}
