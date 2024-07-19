package vn.unigap.api.dto.in.Resume;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDtoIn {
    @Min(0)
    private long seekerId;
    @NotEmpty
    private String careerObj;
    @NotEmpty
    private String title;
    @Min(0)
    private Integer salary;
    @NotNull
    private List<Integer> fieldIds;
    @NotNull
    private List<Integer> provinceIds;
}
