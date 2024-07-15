package com.example.unigap.api.service.seeker;


import com.example.unigap.common.dto.PageDtoOut;
import com.example.unigap.api.dto.in.page.PageSeekerDtoIn;
import com.example.unigap.api.dto.in.SeekerDtoIn;
import com.example.unigap.api.dto.out.pagedata.DataSeeker;
import com.example.unigap.api.dto.out.SeekerDtoOut;

public interface SeekerService {
  SeekerDtoOut get(Integer id);
  SeekerDtoOut create(SeekerDtoIn dto);
  SeekerDtoOut update(Integer id, SeekerDtoIn dto);
  SeekerDtoOut delete(Integer id);
  PageDtoOut<DataSeeker> list(PageSeekerDtoIn dto);

}
