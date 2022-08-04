package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {


    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Alias> result = repository.findAll();
        int expected = 2;
        int actual = result.size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        // all fields
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(3, actual.getAliasId());
    }

    private Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Big");
        alias.setPersona("Dawggg");
        alias.setAgentId(2);
        return alias;
    }

}