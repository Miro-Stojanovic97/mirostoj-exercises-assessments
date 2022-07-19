package learn.foraging.data;

import learn.foraging.models.Forager;

import java.util.List;

public interface ForagerRepository {
    Forager findById(String id);

    //Add Forager
    Forager add(Forager forager) throws DataException;

    List<Forager> findAll();

    List<Forager> findByState(String stateAbbr);
}
