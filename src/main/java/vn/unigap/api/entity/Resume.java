package vn.unigap.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "resume")
public class Resume implements Serializable {
    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", initialValue = 8514317)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "seeker_id")
    private long seekerId;
    @Column(name = "career_obj")
    private String careerObj;
    @Column(name = "title")
    private String title;
    @Column(name = "salary")
    private Integer salary;
    @Column(name="fields")
    private String fields;
    @Column(name="provinces")
    private String provinces;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seeker_id", insertable = false, updatable = false)
    private Seeker seeker;

}
