package com.example.unigap.api.service.employer;

import com.example.unigap.api.dto.in.EmployerDtoIn;
import com.example.unigap.api.dto.in.page.PageEmployerDtoIn;
import com.example.unigap.api.dto.in.UpdateEmployerDtoIn;
import com.example.unigap.api.dto.out.pagedata.DataEmployer;
import com.example.unigap.api.dto.out.EmployerDtoOut;
import com.example.unigap.common.dto.PageDtoOut;

public interface EmployerService {
    EmployerDtoOut get(Integer id);
    EmployerDtoOut create(EmployerDtoIn dto);
    EmployerDtoOut update(Integer id, UpdateEmployerDtoIn dto);
    EmployerDtoOut delete(Integer id);
    PageDtoOut<DataEmployer> list(PageEmployerDtoIn dto);


}
