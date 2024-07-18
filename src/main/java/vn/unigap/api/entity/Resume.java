package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "resume")
public class Resume {
    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
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
    private Date created_at;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;
}
