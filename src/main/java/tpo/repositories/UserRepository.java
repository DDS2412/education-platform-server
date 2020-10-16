package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Integer> {
    Optional<User> getFirstByLogin(String login);

    User findByLogin(String login);

    Optional<User> findByToken(String token);
}
