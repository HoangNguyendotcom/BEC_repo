package vn.unigap.api.entity;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "Employer")
public class Employer implements Serializable {
    @Id
    @SequenceGenerator(name = "employer_sequence", sequenceName = "employers_sequence", initialValue = 3094562)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "province")
    private int province;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs = new ArrayList<>();
}
