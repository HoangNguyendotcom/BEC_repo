package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Seeker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Integer countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

//    @Query("SELECT s FROM Seeker s JOIN Resume r ON s.id = r.seeker.id " +
//                "WHERE r.salary <= :salary " +
//                "AND (:fieldIds IS NULL OR r.fields IN :fieldIds) " +
//                "AND (:provinceIds IS NULL OR r.provinces IN :provinceIds)")
//    List<Seeker> findAllBySalaryAndProvincesAndFields(@Param("salary") Integer salary,
//                                                   @Param("provinceIds") List<Integer> provinceIds,
//                                                   @Param("fieldIds") List<Integer> fieldIds);
}
