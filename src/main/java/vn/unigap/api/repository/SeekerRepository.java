package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Seeker;

import java.time.LocalDateTime;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {
    Integer countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
