package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.Chat;
import tpo.domains.User;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepositoryImplementation<Chat, Integer> {
    List<Chat> findAllByUsers(List<User> users);
}
