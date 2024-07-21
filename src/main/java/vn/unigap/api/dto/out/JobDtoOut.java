package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unigap.api.entity.Job;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.entity.Employer;
import vn.unigap.common.Holder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoOut {
    private long id;
    private String title;
    private long employerId;
    private String employerName;
    private Integer quantity;
    private String description;
    private String fields;
    private String provinces;
    private Integer salary;
    private Date expiredAt;

    public static JobDtoOut from (Job j) {
        EmployerRepository employerRepository = Holder.getEmployerRepository();

        long employerId = employerRepository.findById(j.getEmployer().getId())
                    .map(Employer::getId)
                    .orElse(0L);
        String employerName = employerRepository.findById(j.getEmployer().getId())
                    .map(Employer::getName)
                    .orElse(null);



        return JobDtoOut.builder()
                .id(j.getId())
                .title(j.getTitle())
                .quantity(j.getQuantity())
                .description(j.getDescription())
                .salary(j.getSalary())
                .fields(j.getFields())
                .provinces(j.getProvinces())
                .expiredAt(j.getExpiredAt())
                .employerId(employerId)
                .employerName(employerName)
                .build();
    }
}
