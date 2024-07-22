package vn.unigap.api.repository.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import vn.unigap.api.entity.jpa.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Integer countByCreatedAtBetween(Date start, Date end);

    @NotNull
    @Cacheable(value = "Job", key = "#id")
    Optional<Job> findById(@NotNull Long id);
}
