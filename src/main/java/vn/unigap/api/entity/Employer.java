package vn.unigap.api.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "Employer")
public class Employer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "employer_sequence", sequenceName = "employer_sequence", initialValue = 3094562)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_sequence")
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
    private Date createdAt = new Date();
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

//    @OneToMany(mappedBy = "employer")
//    private List<Job> jobs = new ArrayList<>();
}
