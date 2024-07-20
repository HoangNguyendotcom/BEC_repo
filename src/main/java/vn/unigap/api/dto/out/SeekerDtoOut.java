package vn.unigap.api.dto.out;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.common.Holder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoOut {

    @Id
    @Min(1)
    private long id;

    private String name;
    private String birthday;
    private String address;
    private Integer provinceId;
    private String provinceName;

    public static SeekerDtoOut from (Seeker s) {
        ProvinceRepository provinceRepository = Holder.getProvinceRepository();
        String provinceName = provinceRepository.findById(s.getProvince().getId())
                .map(Province::getName)
                .orElse(null);
        return SeekerDtoOut.builder()
                .id(s.getId())
                .name(s.getName())
                .birthday(s.getBirthday())
                .address(s.getAddress())
                .provinceId(s.getProvince().getId())
                .provinceName(provinceName)
                .build();
    }
}
