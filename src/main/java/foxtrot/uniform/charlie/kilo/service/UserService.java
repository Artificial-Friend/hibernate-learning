package foxtrot.uniform.charlie.kilo.service;

import foxtrot.uniform.charlie.kilo.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> get(Long id);
}
