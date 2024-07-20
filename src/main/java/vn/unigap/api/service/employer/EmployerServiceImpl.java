package vn.unigap.api.service.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Employer.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.Employer.EmployerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.service.base.BaseRedisService;
import vn.unigap.api.service.base.BaseRedisServiceImpl;
import vn.unigap.common.data_transform.Converter;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;
import java.util.Date;


@Service
public class EmployerServiceImpl extends BaseRedisServiceImpl implements EmployerService {
    @Autowired
    public EmployerServiceImpl(RedisTemplate<String, Object> redisTemplate, EmployerRepository employerRepository) {
        super(redisTemplate);
        this.employerRepository = employerRepository;
    }

    public EmployerRepository employerRepository;

    @Override
    public PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Employer> employers = this.employerRepository
                .findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), employers.getTotalElements(),
                employers.stream().map(EmployerDtoOut::from).toList());
    }

    @Override
    public EmployerDtoOut getEmployerByID(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer Not Found"));
        this.hashSet(id.toString(), employer.getName(), employer.getEmail());
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
                .createdAt(new Date())
                .updatedAt(new Date())
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
        employer.setUpdatedAt(new Date());

        return EmployerDtoOut.from(employer);
    }

    @Override
    public void deleteEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Employer not found"));
        employerRepository.delete(employer);
    }
}