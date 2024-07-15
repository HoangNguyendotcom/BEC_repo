package com.example.unigap.api.dto.out.pagedata;

import com.example.unigap.common.holder.Holder;
import com.example.unigap.api.entity.Employer;
import com.example.unigap.api.entity.Province;
import com.example.unigap.api.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataEmployer {
    private Integer id;
    private String email;
    private String name;
    private Integer provinceId;
    private String provinceName;

    public static DataEmployer fromEmployer(Employer employer){
      ProvinceRepository provinceRepository = Holder.getProvinceRepository();
      String provinceName = provinceRepository.findById(employer.getProvince())
        .map(Province::getName)
        .orElse(null);

        return DataEmployer.builder()
                .id(employer.getId())
                .email(employer.getEmail())
                .name(employer.getName())
                .provinceId(employer.getProvince())
                .provinceName(provinceName)
                .build();
    }
}
