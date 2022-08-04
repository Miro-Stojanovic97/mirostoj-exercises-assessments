package pets.data;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pets.models.Pet;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
@Profile("jdbc-template")
public class PetJdbcTemplateRepository implements PetRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Pet> mapper = (resultSet, rowNum) -> {
        Pet pet = new Pet();
        pet.setPetId(resultSet.getInt("pet_id"));
        pet.setName(resultSet.getString("name"));
        pet.setType(resultSet.getString("type"));
        return pet;
    };

    public PetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> findAll() {
        final String sql = "Select * from pet;";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public boolean update(Pet pet) {
        final String sql = "update pet set"
                + "`name` = ?,"
                + "`type` = ?"
                + " where pet_id = ?;";
        int rowsUpdated = jdbcTemplate.update(sql, pet.getName(), pet.getType(), pet.getPetId());
        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int petId) {
      final String sql = "delete from pet where pet_id = ?;";
      return jdbcTemplate.update(sql, petId) > 0;
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, `type`) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getType());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        pet.setPetId(keyHolder.getKey().intValue());
        return pet;
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "select pet_id, `name`, `type` from pet where pet_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, mapper, petId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
