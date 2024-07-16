package vn.unigap.api.dto.in;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployerDtoIn {
    @Id
    @NotEmpty
    private long id;
    @NotEmpty
    @Size(max = 255)
    private String name;
    @NotEmpty
    private int provinceId;

    private String description;
}
