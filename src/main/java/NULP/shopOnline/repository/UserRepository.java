package NULP.shopOnline.repository;



import NULP.shopOnline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(long id);

    void deleteUserById( long id);
}
