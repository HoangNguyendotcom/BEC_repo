package com.example.unigap.api.service.resume;

import com.example.unigap.common.dto.PageDtoOut;
import com.example.unigap.api.dto.in.ResumeDtoIn;
import com.example.unigap.api.dto.in.page.PageResumeDtoIn;
import com.example.unigap.api.dto.in.UpdateResumeDtoIn;
import com.example.unigap.api.dto.out.pagedata.DataResume;
import com.example.unigap.api.dto.out.ResumeDtoOut;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {
  ResumeDtoOut get(Integer id);
  ResumeDtoOut create(ResumeDtoIn dto);
  ResumeDtoOut update(Integer id, UpdateResumeDtoIn dto);
  ResumeDtoOut delete(Integer id);
  PageDtoOut<DataResume> list(PageResumeDtoIn dto);
}
