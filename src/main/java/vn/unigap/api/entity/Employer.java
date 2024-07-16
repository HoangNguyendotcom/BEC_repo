package vn.unigap.api.entity;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "Employer")
public class Employer implements Serializable {
    @Id
    @SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", initialValue = 3094562)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "province_id")
    private int provinceId;
    @Column(name = "description")
    private String description;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
