//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.domain;

import learn.house.data.GuestRepository;
import learn.house.models.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) { this.repository = repository; }

    public Guest findByEmail(String email) { return repository.findByEmail(email); }

}
