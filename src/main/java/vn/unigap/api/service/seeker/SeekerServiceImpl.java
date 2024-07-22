package vn.unigap.api.service.seeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Seeker.SeekerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.entity.Province;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.common.errorcode.ErrorCode;
import vn.unigap.common.exception.ApiException;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerRepository seekerRepository;

    @Autowired
    public SeekerServiceImpl(SeekerRepository seekerRepository) {
        this.seekerRepository = seekerRepository;
    }

    @Autowired
    public ProvinceRepository provinceRepository;

    @Override
    public PageDtoOut<SeekerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Seeker> seekers = this.seekerRepository.findAll(
                PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(), Sort.by("name").descending()));
        return PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), seekers.getTotalElements(),
                seekers.stream().map(SeekerDtoOut::from).toList());
    }

    @Override
    public SeekerDtoOut getSeekerById(long id) {
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker Not Found"));
        return SeekerDtoOut.from(seeker);
    }

    @Override
    public SeekerDtoOut create(SeekerDtoIn seekerDtoIn) {
        Province province = provinceRepository.findById(seekerDtoIn.getProvinceId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Province Not Found"));
        Seeker seeker = seekerRepository.save(Seeker.builder().name(seekerDtoIn.getName())
                .birthday(seekerDtoIn.getBirthday()).address(seekerDtoIn.getAddress()).province(province).build());
        return SeekerDtoOut.from(seeker);
    }

    @Override
    public SeekerDtoOut update(Long id, SeekerDtoIn seekerDtoIn) {
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "seeker not found"));
        Province province = provinceRepository.findById(seekerDtoIn.getProvinceId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Province Not Found"));

        seeker.setAddress(seekerDtoIn.getAddress());
        seeker.setBirthday(seekerDtoIn.getBirthday());
        seeker.setProvince(province);
        return SeekerDtoOut.from(seeker);
    }

    @Override
    public void deleteSeeker(Long id) {
        Seeker seeker = seekerRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "Seeker not found"));
        seekerRepository.delete(seeker);
    }
}
