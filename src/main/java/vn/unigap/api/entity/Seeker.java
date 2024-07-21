package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "seeker")
public class Seeker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seeker_sequence", sequenceName = "seeker_sequence", initialValue = 4452947)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seeker_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "address")
    private String address;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @ManyToOne
    @JoinColumn(name = "province")
    private Province province;
}
