package vn.unigap.api.entity;

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
@Table(name = "seeker")
public class JobSeeker implements Serializable {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
}