package vn.unigap.api.service.job;

import vn.unigap.api.dto.in.Job.JobDtoIn;
import vn.unigap.api.dto.in.Job.UpdateJobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.*;

public interface JobService {
    PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn);

    JobDtoOut getJobById(long id);

    JobDtoOut create(JobDtoIn jobDtoIn);

    JobDtoOut update(Long id, UpdateJobDtoIn updateJobDtoIn);

    void deleteJob(Long id);

    JobSeekerDtoOut getSuitableSeekers(long id);
}
