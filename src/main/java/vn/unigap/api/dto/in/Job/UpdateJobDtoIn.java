package vn.unigap.api.dto.in.Job;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateJobDtoIn {
    @NotEmpty
    @Size(max = 255)
    private String title;
    @Id
    @Min(1)
    private long Id;

    private long employerId;

    @Min(1)
    private int quantity;

    private String description;

    private List<Integer> fieldIds;

    private List<Integer> provinceIds;

    private Integer salary;

    @Future
    private Date expiredAt;
}
