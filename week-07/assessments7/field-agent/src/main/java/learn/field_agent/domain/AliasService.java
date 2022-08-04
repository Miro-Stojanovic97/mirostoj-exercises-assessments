package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliasService {

    public AliasRepository repository;
    public AgentService agentService;

    public AliasService(AliasRepository repository, AgentService agentService) {
        this.repository = repository;
        this.agentService = agentService;
    }

    public List<Alias> findAll() {
        return repository.findAll();
    }

    public Alias findById(int aliasId) {
        return repository.findById(aliasId);
    }

    public List<Alias> findByAgentId(int agentId) {
        return repository.findByAgentId(agentId);
    }


    public Result<Alias> add(Alias alias) {
        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() != 0) {
            result.addMessage("Alias Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    private Result<Alias> validate(Alias alias) {
        Result<Alias> result = new Result<>();
        List<Agent> agents = agentService.findAll();
        List<Alias> all = repository.findAll();

        if (alias == null) {
            result.addMessage("Alias cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("Alias name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(alias.getPersona())) {
            if (all.size() != 0) {
                all.forEach(sc -> {
                    if (sc.getName().equals(alias.getName())) {
                        result.addMessage("Persona is required for duplicated name", ResultType.INVALID);
                    }
                });
            }
        }

        if (alias.getAgentId() <= 0) {
            result.addMessage("AgentID is required.", ResultType.INVALID);
        }

        long agentCount = agents.stream()
                .filter(agent -> agent.getAgentId() == alias.getAgentId())
                .count();

        if (agentCount == 0) {
            result.addMessage("No agents found.", ResultType.INVALID);
        }

        return result;
    }


    public Result<Alias> update(Alias alias) {
        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() <= 0) {
            result.addMessage("aliasId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(alias)) {
            String msg = String.format("aliasId: %s, not found", alias.getAliasId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int aliasId) {
        return repository.deleteById(aliasId);
    }

}