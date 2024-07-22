package vn.unigap.api.repository.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.jpa.Seeker;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {
    Integer countByCreatedAtBetween(Date start, Date end);

    @NotNull
    @Cacheable(value = "Seeker", key = "#id")
    Optional<Seeker> findById(@NotNull Long id);

}
