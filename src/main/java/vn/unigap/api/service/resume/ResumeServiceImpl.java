package vn.unigap.api.service.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Resume.ResumeDtoIn;
import vn.unigap.api.dto.in.Resume.UpdateResumeDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.repository.FieldRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.ResumeRepository;

import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.data_transform.Converter;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;
import vn.unigap.common.data_transform.Converter;

import java.util.Date;

import static vn.unigap.common.Holder.*;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Autowired
    public ProvinceRepository provinceRepository;
    @Autowired
    public FieldRepository fieldRepository;
    @Autowired
    public SeekerRepository seekerRepository;

    @Override
    public PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Resume> resumes = this.resumeRepository.findAll(
                PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("title").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), resumes.getTotalElements(),
                resumes.stream().map(ResumeDtoOut::from).toList());
    }

    @Override
    public ResumeDtoOut getResumeById(long id) {
        Resume resume = this.resumeRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Resume Not Found"));
        return ResumeDtoOut.from(resume);
    }

    @Override
    public ResumeDtoOut create(ResumeDtoIn resumeDtoIn) {
        for (Integer pid : resumeDtoIn.getProvinceIds()) {
            if (!provinceRepository.existsById(pid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "province ID " + pid + " not found");
            }
        }

        for (Integer fid : resumeDtoIn.getFieldIds()) {
            if (!fieldRepository.existsById(fid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "field ID " + fid + " not found");
            }
        }

        if (!seekerRepository.existsById(resumeDtoIn.getSeekerId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "seeker not found");
        }
        Resume resume = resumeRepository.save(Resume.builder().seekerId(resumeDtoIn.getSeekerId())
                .careerObj(resumeDtoIn.getCareerObj()).title(resumeDtoIn.getTitle()).salary(resumeDtoIn.getSalary())
                .fields(Converter.ListToStringDb(resumeDtoIn.getFieldIds()))
                .provinces(Converter.ListToStringDb(resumeDtoIn.getProvinceIds())).createdAt(new Date())
                .updatedAt(new Date()).build());
        return ResumeDtoOut.from(resume);
    }

    @Override
    public ResumeDtoOut update(Long id, UpdateResumeDtoIn updateResumeDtoIn) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "resume not found"));

        for (Integer pid : updateResumeDtoIn.getProvinceIds()) {
            if (!provinceRepository.existsById(pid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "province ID " + pid + " not found");
            }
        }

        for (Integer fid : updateResumeDtoIn.getFieldIds()) {
            if (!fieldRepository.existsById(fid)) {
                throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "field ID " + fid + " not found");
            }
        }
        resume.setCareerObj(updateResumeDtoIn.getCareerObj());
        resume.setTitle(updateResumeDtoIn.getTitle());
        resume.setSalary(updateResumeDtoIn.getSalary());
        resume.setFields(Converter.ListToStringDb(updateResumeDtoIn.getFieldIds()));
        resume.setProvinces(Converter.ListToStringDb(updateResumeDtoIn.getProvinceIds()));
        resume.setUpdatedAt(new Date());

        return ResumeDtoOut.from(resume);
    }

    @Override
    public void deleteResume(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "resume not found"));
        resumeRepository.delete(resume);
    }
}
