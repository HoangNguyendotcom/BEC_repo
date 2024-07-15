package com.example.unigap.api.service.job;

import com.example.unigap.common.dto.PageDtoOut;
import com.example.unigap.api.dto.in.JobDtoIn;
import com.example.unigap.api.dto.in.page.PageJobDtoIn;
import com.example.unigap.api.dto.out.pagedata.DataJob;
import com.example.unigap.api.dto.out.JobDtoOut;

public interface JobService {
  JobDtoOut get(Integer id);
  JobDtoOut create(JobDtoIn dto);
  JobDtoOut update(Integer id, JobDtoIn dto);
  JobDtoOut delete(Integer id);
  PageDtoOut<DataJob> list(PageJobDtoIn dto);

}
