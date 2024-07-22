package vn.unigap.api.repository.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.jpa.Resume;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Integer countByCreatedAtBetween(Date start, Date end);

    @NotNull
    @Cacheable(value = "Resume", key = "#id")
    Optional<Resume> findById(@NotNull Long id);
}
