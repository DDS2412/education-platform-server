package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.UserTask;

@Repository
public interface UserTaskRepository extends JpaRepositoryImplementation<UserTask, Integer> {
}
