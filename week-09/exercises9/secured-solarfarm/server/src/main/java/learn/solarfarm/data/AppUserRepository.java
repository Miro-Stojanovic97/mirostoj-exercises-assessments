package learn.solarfarm.data;

import learn.solarfarm.models.AppUser;

public interface AppUserRepository {

    AppUser findByUsername(String username);

    AppUser add(AppUser user);

    boolean update(AppUser user);
}
