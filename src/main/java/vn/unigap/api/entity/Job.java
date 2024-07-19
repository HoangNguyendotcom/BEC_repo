package vn.unigap.api.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.unigap.api.entity.JobSeeker;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "Jobs")
public class Job implements Serializable {
    @Id
    @SequenceGenerator(name = "jobs_sequence", sequenceName = "jobs_sequence", initialValue = 449936)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobs_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "employer_id")
    private long employerId;
    @Column(name = "title")
    private String title;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "fields")
    private String fields;
    @Column(name = "provinces")
    private String provinces;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", insertable = false, updatable = false)
    private Employer employer;

    private List<JobSeeker> seekers;
}
