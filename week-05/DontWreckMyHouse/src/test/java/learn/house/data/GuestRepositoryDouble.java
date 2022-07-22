package learn.house.data;

import learn.house.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {

    @Override
    public List<Guest> findAll() {
        List<Guest> result = new ArrayList<>();
        result.add(new Guest(1, "Miro", "Stoj", "mir32@gmail.com", "111-222-3333"));
        result.add(new Guest(2, "Miko", "Stolowski", "mirslow12@gmail.com", "111-777-8888"));
        result.add(new Guest(3, "Mijo", "Stolijo", "mijj34@gmail.com", "111-555-6666"));
        return result;
    }

    @Override
    public Guest findById(int guestId) { return null; }

    @Override
    public Guest findByEmail(String email) { return null; }

}
