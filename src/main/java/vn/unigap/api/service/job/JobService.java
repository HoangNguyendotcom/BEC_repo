package vn.unigap.api.service.job;

import vn.unigap.api.dto.in.Employer.EmployerDtoIn;
import vn.unigap.api.dto.in.Employer.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.Job.JobDtoIn;
import vn.unigap.api.dto.in.Job.UpdateJobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.JobDtoOut;

public interface JobService {
    PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn);
    JobDtoOut get(long id);
    JobDtoOut create(JobDtoIn jobDtoIn);

    JobDtoOut update(Long id, UpdateJobDtoIn updateJobDtoIn);

    void deleteJob(Long id);
}
