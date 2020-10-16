package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.Test;
import tpo.domains.User;
import tpo.domains.UserTest;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepositoryImplementation<UserTest, Integer> {
    Optional<UserTest> findByUserAndTest(User user, Test test);

    List<UserTest> findAllByUser(User user);
}
