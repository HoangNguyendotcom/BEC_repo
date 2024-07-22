package vn.unigap.api.entity.jpa;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "job_field")
public class Field implements Serializable {
    @Id
    @SequenceGenerator(name = "field_sequence", sequenceName = "field_sequence", initialValue = 60)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "field_sequence")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
}
