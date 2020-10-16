package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.Message;

@Repository
public interface MessageRepository extends JpaRepositoryImplementation<Message, Integer> {
}
