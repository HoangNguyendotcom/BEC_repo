package vn.unigap.api.service.resume;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Resume.ResumeDtoIn;
import vn.unigap.api.dto.in.Resume.UpdateResumeDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;

public interface ResumeService {
    PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn);

    ResumeDtoOut getResumeById(long id);

    ResumeDtoOut create(ResumeDtoIn resumeDtoIn);

    ResumeDtoOut update(Long id, UpdateResumeDtoIn updateResumeDtoIn);

    void deleteResume(Long id);
}
