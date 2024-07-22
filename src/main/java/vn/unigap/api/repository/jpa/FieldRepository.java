package vn.unigap.api.repository.jpa;

import vn.unigap.api.entity.jpa.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

}