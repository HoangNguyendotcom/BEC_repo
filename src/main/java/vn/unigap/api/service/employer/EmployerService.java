package vn.unigap.api.service.employer;

import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.in.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;

public interface EmployerService {
    PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
//    EmployerDtoOut get(Long id);
//    EmployerDtoOut create(EmployerDtoIn userDtoIn);
//
//    EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateUserDtoIn);
//
//    void delete(Long id);
}
