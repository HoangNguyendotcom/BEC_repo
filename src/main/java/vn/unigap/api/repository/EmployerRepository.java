package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Employer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{
    Optional<Employer> findByEmail(String email);
}
