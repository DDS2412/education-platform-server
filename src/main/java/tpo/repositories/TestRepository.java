package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import tpo.domains.Test;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepositoryImplementation<Test, Integer> {
    List<Test> getAll();

    Optional<Test> findById(Integer id);
}
