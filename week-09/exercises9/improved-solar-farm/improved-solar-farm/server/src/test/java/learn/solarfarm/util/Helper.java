package learn.solarfarm.util;

import learn.solarfarm.models.AppUser;

import java.util.List;

public class Helper {
    public static AppUser makeAppUser() {
        return new AppUser(1, "555-444-3333", "john@smith.com", "", false, List.of());
    }
}
