package vn.unigap.api.service.auth;

import vn.unigap.api.dto.in.AuthLoginDtoIn;
import vn.unigap.api.dto.out.AuthLoginDtoOut;

public interface AuthService {
    AuthLoginDtoOut login(AuthLoginDtoIn loginDtoIn);
}
