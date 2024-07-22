package vn.unigap.api.dto.in.Seeker;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoIn {
    @NotEmpty
    private String name;
    private String birthday;
    private String address;
    @Min(0)
    private Integer provinceId;
}
