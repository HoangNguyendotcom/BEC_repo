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
    private int province;
    private String description;


    public static EmployerDtoOut from (Employer e) {
        return EmployerDtoOut.builder()
                .id(e.getId())
                .email(e.getEmail())
                .name(e.getName())
                .province(e.getProvince())
                .description(e.getDescription())
                .build();
    }
}
