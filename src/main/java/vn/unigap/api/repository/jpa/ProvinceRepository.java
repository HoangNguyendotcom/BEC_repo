package vn.unigap.api.repository.jpa;

import vn.unigap.api.entity.jpa.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}
