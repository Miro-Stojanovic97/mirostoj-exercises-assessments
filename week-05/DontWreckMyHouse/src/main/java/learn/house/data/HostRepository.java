//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

import learn.house.models.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    Host findByHostId(String hostId);

    Host findByEmail(String email);

}
