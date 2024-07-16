package vn.unigap.api.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.common.Common;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoIn {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @Min(1)
    private int provinceId;

    private String description;

    public String getEmail() {
        return Common.toLowerCase(email);
    }
}
