package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.entity.Field;
import vn.unigap.api.repository.FieldRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.Holder;
import vn.unigap.common.data_transform.Converter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDtoOut {
    private long id;
    private long seekerId;
    private String seekerName;
    private String careerObj;
    private String title;
    private List<Field> fields;
    private List<Province> provinces;
    private Integer salary;

    public static ResumeDtoOut from (Resume r) {
        SeekerRepository seekerRepository = Holder.getSeekerRepository();
        ProvinceRepository provinceRepository = Holder.getProvinceRepository();
        FieldRepository fieldRepository = Holder.getFieldRepository();

        String seekerName = seekerRepository.findById(r.getSeekerId())
                .map(Seeker::getName)
                .orElse(null);

        List<Integer> provinceIds = Converter.extractIdFromStringDb(r.getProvinces());
        List<Integer> fieldIds = Converter.extractIdFromStringDb(r.getFields());

        List<Province> provinces = provinceRepository.findAllById(provinceIds)
                .stream()
                .map(province -> new Province(province.getId(), province.getName()))
                .collect(Collectors.toList());

        List<Field> fields = fieldRepository.findAllById(fieldIds)
                .stream()
                .map(field -> new Field(field.getId(), field.getName()))
                .collect(Collectors.toList());

        return ResumeDtoOut.builder()
                .id(r.getId())
                .seekerId(r.getSeekerId())
                .seekerName(seekerName)
                .careerObj(r.getCareerObj())
                .title(r.getTitle())
                .salary(r.getSalary())
                .fields(fields)
                .provinces(provinces)
                .build();
    }
}
