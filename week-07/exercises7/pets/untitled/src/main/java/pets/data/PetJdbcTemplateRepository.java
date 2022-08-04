package pets.data;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import pets.models.Pet;

import java.sql.PreparedStatement;
import java.util.List;


@Repository
@Profile("jdbc-template")
public class PetJdbcTemplateRepository implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Pet> mapper = (resultSet, rowNum) -> {
        Pet pet = new Pet();
        pet.setPetId(resultSet.getInt("pet_id"));
        pet.setName(resultSet.getString("name"));
        pet.setType(resultSet.getString("type"));
        return pet;
    };

    @Override
    public List<Pet> findAll() {
        final String sql = "Select * from pet;";
        return jdbcTemplate
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "select pet_id, `name`, `type` from pet where pet_id = ?;";
        try {
            return jdbcTem
        }
    }

    @Override
    public boolean update(Pet pet) {
        return false;
    }

    @Override
    public boolean deleteById(int petId) {
        return false;
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, `type`) values (?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement
        })
        }
    }
}
