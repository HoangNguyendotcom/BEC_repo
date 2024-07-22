package vn.unigap.common;

import vn.unigap.api.repository.jpa.EmployerRepository;
import vn.unigap.api.repository.jpa.FieldRepository;
import vn.unigap.api.repository.jpa.ProvinceRepository;
import vn.unigap.api.repository.jpa.SeekerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Holder {

    @Getter
    private static ProvinceRepository provinceRepository;

    @Getter
    private static FieldRepository fieldRepository;

    @Getter
    private static SeekerRepository seekerRepository;

    @Getter
    private static EmployerRepository employerRepository;

    @Autowired
    public void setProvinceRepository(ProvinceRepository provinceRepository) {
        Holder.provinceRepository = provinceRepository;
    }

    @Autowired
    private void setFieldRepository(FieldRepository fieldRepository) {
        Holder.fieldRepository = fieldRepository;
    }

    @Autowired
    private void setEmployerRepository(EmployerRepository employerRepository) {
        Holder.employerRepository = employerRepository;
    }

    @Autowired
    private void setSeekerRepository(SeekerRepository seekerRepository) {
        Holder.seekerRepository = seekerRepository;
    }
}
