package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import vn.unigap.api.entity.Employer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Long id;
    private String email;
    private String name;
    private int provinceId;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static EmployerDtoOut from (Employer e) {
        return EmployerDtoOut.builder()
                .id(e.getId())
                .email(e.getEmail())
                .name(e.getName())
                .provinceId(e.getProvinceId())
                .description(e.getDescription())
                .createAt(e.getCreatedAt())
                .updateAt(e.getUpdatedAt())
                .build();
    }
}
