package learn.foraging.domain;

import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import learn.foraging.data.DataException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.add(forager));
        return result;
    }

    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result<>();

        validateFields(forager, result);
        if (!result.isSuccess()) {
            return result;
        }
        validateNotDuplicate(forager, result);
        return result;
    }

    private void validateNotDuplicate(Forager forager, Result<Forager> result) {
        List<Forager> foragers = repository.findAll();

        if(foragers.size() == 0) return;

        for (Forager f : foragers) {
            if (f.getFirstName().equalsIgnoreCase(forager.getFirstName())
                && f.getLastName().equalsIgnoreCase(forager.getLastName())
                &&f.getState().equalsIgnoreCase(forager.getState())) {
                result.addErrorMessage("This forager already exists.");
            }
        }
    }

    private void validateFields(Forager forager, Result<Forager> result) {
        if (forager.getFirstName().isBlank()
        || forager.getFirstName().length() == 0) {
            result.addErrorMessage("Forager first name must be entered.");
        }

        if (forager.getLastName().isBlank()
                || forager.getLastName().length() == 0) {
            result.addErrorMessage("Forager last name must be entered.");
        }

        if (forager.getState().isBlank()
                || forager.getState().length() == 0) {
            result.addErrorMessage("Forager state must be entered.");
        }
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findAll() {return repository.findAll(); }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }
}
