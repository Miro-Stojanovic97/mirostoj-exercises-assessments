//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.domain;

import learn.house.data.HostRepository;
import learn.house.models.Host;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) { this.repository = repository; }

    public Host findByEmail(String email) { return repository.findByEmail(email); }

}
