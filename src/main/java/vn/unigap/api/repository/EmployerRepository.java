package vn.unigap.api.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Employer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{
    Optional<Employer> findByEmail(String email);

    Page<Employer> findAll(Pageable page);

    @Override
    @Cacheable(value = "Employer", key = "#id")
    Optional<Employer> findById(Long id);
}
