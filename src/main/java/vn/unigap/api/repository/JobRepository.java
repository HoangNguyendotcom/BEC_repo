package vn.unigap.api.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Seeker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Integer countByCreatedAtBetween(Date start, Date end);

    @NotNull
    @Cacheable(value = "Job", key = "#id")
    Optional<Job> findById(@NotNull Long id);
}
