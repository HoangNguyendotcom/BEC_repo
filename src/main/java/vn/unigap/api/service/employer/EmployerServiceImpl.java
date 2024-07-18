package vn.unigap.api.service.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Employer.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.Employer.EmployerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;
import java.time.LocalDateTime;


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

    @Override
    public EmployerDtoOut get(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer Not Found"));
        return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut create(EmployerDtoIn employerDtoIn) {
        employerRepository.findByEmail(employerDtoIn.getEmail()).ifPresent(employer -> {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "email already existed");
        });
        Employer employer = employerRepository.save(Employer.builder()
                .email(employerDtoIn.getEmail())
                .name(employerDtoIn.getName())
                .province(employerDtoIn.getProvince())
                .description(employerDtoIn.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
        return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateEmployerDtoIn) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));
        employer.setName(updateEmployerDtoIn.getName());
        employer.setProvince(updateEmployerDtoIn.getProvinceId());
        employer.setDescription(updateEmployerDtoIn.getDescription());
        employer.setUpdatedAt(LocalDateTime.now());

        return EmployerDtoOut.from(employer);
    }

    @Override
    public void deleteEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));
        employerRepository.delete(employer);
    }
}