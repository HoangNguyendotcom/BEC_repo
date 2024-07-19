package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Resume;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Page<Resume> findAllBySeekerId(Integer id, Pageable page);
    Integer countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
