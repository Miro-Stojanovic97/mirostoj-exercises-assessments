package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {

    List<Alias> aliases = List.of(
            new Alias(1, "Fuzzy", null, 1),
            new Alias(2, "Stevens", null, 2)
    );

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldNotAddWhenInvalid() {
        when(repository.findAll()).thenReturn(aliases);

        Alias alias = makeAlias();
        alias.setName(" ");

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setAgentId(9);

        actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setName("Fuzzy");
        alias.setPersona(null);

        actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType(), "Duplicate name with no persona was added!");
    }

    @Test
    void shouldAddWhenValid() {
        Alias alias = makeAlias();

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Alias alias = new Alias(1, "TEST NAME", "TEST PERSONA", 1);

        when(repository.update(alias)).thenReturn(false);
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

        alias = new Alias(1, null, "TEST PERSONA", 1);

        actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias.setName(" ");
        actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias.setAliasId(0);
        actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdateWhenValid() {
        Alias alias = new Alias(1, "TEST NAME", "TEST PERSONA", 1);

        when(repository.update(alias)).thenReturn(true);
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    private Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Fuzzy");
        alias.setPersona("Persona");
        alias.setAgentId(1);
        return alias;
    }
}