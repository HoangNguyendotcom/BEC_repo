package vn.unigap.api.service.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.common.Common;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Employer> employers = this.employerRepository
                .findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), employers.getTotalElements(),
                employers.stream().map(EmployerDtoOut::from).toList());
    }

//    @Override
//    public EmployerDtoOut get(Long id) {
//        return null;
//    }
//
//    @Override
//    public EmployerDtoOut create(EmployerDtoIn userDtoIn) {
//        return null;
//    }
//
//    @Override
//    public EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateUserDtoIn) {
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {

//    }
}