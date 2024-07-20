package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.*;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.FieldRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.Holder;
import vn.unigap.common.data_transform.Converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSeekerDtoOut {
    private long id;
    private String title;
    private long employerId;
    private String employerName;
    private Integer quantity;
    private List<Field> fields;
    private List<Province> provinces;
    private Integer salary;
    private Date expiredAt;
    private List<JobSeeker> seekers;

    public static JobSeekerDtoOut from(Job j) {
        EmployerRepository employerRepository = Holder.getEmployerRepository();
        ProvinceRepository provinceRepository = Holder.getProvinceRepository();
        FieldRepository fieldRepository = Holder.getFieldRepository();

        String employerName = employerRepository.findById(j.getEmployerId())
                .map(Employer::getName)
                .orElse(null);

        List<Integer> provinceIds = Converter.extractIdFromStringDb(j.getProvinces());
        List<Integer> fieldIds = Converter.extractIdFromStringDb(j.getFields());

        List<Province> provinces = provinceRepository.findAllById(provinceIds)
                .stream()
                .map(province -> new Province(province.getId(), province.getName()))
                .collect(Collectors.toList());

        List<Field> fields = fieldRepository.findAllById(fieldIds)
                .stream()
                .map(field -> new Field(field.getId(), field.getName()))
                .collect(Collectors.toList());

        return JobSeekerDtoOut.builder()
                .id(j.getId())
                .title(j.getTitle())
                .quantity(j.getQuantity())
                .salary(j.getSalary())
                .fields(fields)
                .provinces(provinces)
                .expiredAt(j.getExpiredAt())
                .employerId(j.getEmployerId())
                .employerName(employerName)
                .seekers(j.getSeekers())
                .build();
    }

}
