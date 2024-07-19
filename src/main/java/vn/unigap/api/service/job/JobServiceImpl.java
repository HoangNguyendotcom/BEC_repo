package vn.unigap.api.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import vn.unigap.api.dto.in.Job.JobDtoIn;
import vn.unigap.api.dto.in.Job.UpdateJobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;

import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.dto.out.JobSeekerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;

import vn.unigap.api.entity.*;
import vn.unigap.api.repository.*;
import vn.unigap.common.data_transform.Converter;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;
import vn.unigap.common.data_transform.Converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository){ this.jobRepository = jobRepository;}
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SeekerRepository seekerRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Job> jobs = this.jobRepository
                .findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("expiredAt").descending()));
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), jobs.getTotalElements(),
                jobs.stream().map(JobDtoOut::from).toList());
    }

    @Override
    public JobDtoOut getJobById(long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job Not Found"));
        return JobDtoOut.from(job);
    }

    @Override
    public JobDtoOut create(JobDtoIn jobDtoIn) {
        for (Integer pid : jobDtoIn.getProvinceIds()) {
            if (!provinceRepository.existsById(pid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "province ID " + pid + " not found");
            }
        }

        for (Integer fid : jobDtoIn.getFieldIds()) {
            if (!fieldRepository.existsById(fid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "field ID " + fid + " not found");
            }
        }

        if (!employerRepository.existsById(jobDtoIn.getEmployerId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "employer not found");
        }
        Job job = jobRepository.save(Job.builder()
                .title(jobDtoIn.getTitle())
                .employerId(jobDtoIn.getEmployerId())
                .quantity(jobDtoIn.getQuantity())
                .description(jobDtoIn.getDescription())
                .fields(Converter.ListToStringDb(jobDtoIn.getFieldIds()))
                .provinces(Converter.ListToStringDb(jobDtoIn.getProvinceIds()))
                .salary(jobDtoIn.getSalary())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .expiredAt(jobDtoIn.getExpiredAt())
                .build());
        return JobDtoOut.from(job);
    }

    @Override
    public JobDtoOut update(Long id, UpdateJobDtoIn updateJobDtoIn) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "job not found"));

        for (Integer pid : updateJobDtoIn.getProvinceIds()) {
            if (!provinceRepository.existsById(pid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "province ID " + pid + " not found");
            }
        }

        for (Integer fid : updateJobDtoIn.getFieldIds()) {
            if (!fieldRepository.existsById(fid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "field ID " + fid + " not found");
            }
        }

        if (!employerRepository.existsById(updateJobDtoIn.getEmployerId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "employer not found");
        }
        job.setTitle(updateJobDtoIn.getTitle());
        job.setQuantity(updateJobDtoIn.getQuantity());
        job.setDescription(updateJobDtoIn.getDescription());
        job.setFields(Converter.ListToStringDb(updateJobDtoIn.getFieldIds()));
        job.setProvinces(Converter.ListToStringDb(updateJobDtoIn.getProvinceIds()));
        job.setSalary(updateJobDtoIn.getSalary());
        job.setExpiredAt(updateJobDtoIn.getExpiredAt());
        job.setUpdatedAt(LocalDateTime.now());

        return JobDtoOut.from(job);
    }
    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job not found"));
        jobRepository.delete(job);
    }

    @Override
    public JobSeekerDtoOut getSuitableSeekers(long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Job Not Found"));
        List<Integer> provinceIds = Converter.extractIdFromStringDb(job.getProvinces());
        List<Integer> fieldIds = Converter.extractIdFromStringDb(job.getFields());
        List<Seeker> seekers = findBySalaryAndProvincesAndFields(job.getSalary(), provinceIds, fieldIds);
        List<JobSeeker> jobseekers = seekers
                                        .stream()
                                        .map(seeker -> new JobSeeker(seeker.getId(), seeker.getName()))
                                        .toList();
        job.setSeekers(jobseekers);
        return JobSeekerDtoOut.from(job);
    }

    public List<Seeker> findBySalaryAndProvincesAndFields(Integer salary, List<Integer> provinceIds, List<Integer> fieldIds) {
        String baseQuery = "select s.* from seeker s "
                + "inner join resume r on s.id = r.seeker_id "
                + "where r.salary <= :salary ";

        StringBuilder fieldQuery = new StringBuilder();
        for (int i = 0; i < fieldIds.size(); i++) {
            fieldQuery.append("r.fields like :field").append(i);
            if (i < fieldIds.size() - 1) {
                fieldQuery.append(" or ");
            }
        }
        fieldQuery.append(") and (");
        for (int i = 0; i < provinceIds.size(); i++) {
            fieldQuery.append("r.provinces like :province").append(i);
            if (i < provinceIds.size() - 1) {
                fieldQuery.append(" or ");
            }
        }

        Query query = entityManager.createNativeQuery(baseQuery + " and (" + fieldQuery.toString() + ")", Seeker.class);
        query.setParameter("salary", salary);
        for (int i = 0; i < fieldIds.size(); i++) {
            query.setParameter("field" + i, "%-" + fieldIds.get(i) + "-%");
        }
        for (int i = 0; i < provinceIds.size(); i++) {
            query.setParameter("province" + i, "%-" + provinceIds.get(i) + "-%");
        }

        return query.getResultList();

    }
}
