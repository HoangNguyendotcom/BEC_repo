package vn.unigap.api.dto.in.Job;

import jakarta.validation.constraints.*;
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
public class JobDtoIn {

    private String title;
    @NotNull
    private long employerId;
    private Integer quantity;
    private String description;
    @NotEmpty
    private List<Integer> fieldIds ;
    @NotEmpty
    private List<Integer> provinceIds;
    private Integer salary;
    @Future
    private Date expiredAt;

}
