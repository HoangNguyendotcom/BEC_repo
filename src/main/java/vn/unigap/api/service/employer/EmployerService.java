package vn.unigap.api.service.employer;

import vn.unigap.api.dto.in.Employer.EmployerDtoIn;
import vn.unigap.api.dto.in.Employer.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;

public interface EmployerService {
    PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
    EmployerDtoOut getEmployerById(Long id);
    EmployerDtoOut create(EmployerDtoIn employerDtoIn);

    EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateEmployerDtoIn);
    void deleteEmployer(Long id);
}
