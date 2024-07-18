package vn.unigap.api.service.seeker;

import vn.unigap.api.dto.in.Seeker.SeekerDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;

public interface SeekerService {
    PageDtoOut<SeekerDtoOut> list(PageDtoIn pageDtoIn);
    SeekerDtoOut getSeekerById(long id);
    SeekerDtoOut create(SeekerDtoIn userDtoIn);
    SeekerDtoOut update(Long id, SeekerDtoIn seekerDtoIn);
    void deleteSeeker(Long id);
}