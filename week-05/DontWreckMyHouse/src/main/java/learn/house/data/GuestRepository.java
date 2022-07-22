//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.data;

import learn.house.models.Guest;

import java.util.List;

public interface GuestRepository {

    List<Guest> findAll();

    Guest findById(int guestId);

    Guest findByEmail(String email);

}
