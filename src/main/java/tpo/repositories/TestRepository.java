package tpo.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import tpo.domains.Test;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepositoryImplementation<Test, Integer> {
    Optional<Test> findById(Integer id);
}
