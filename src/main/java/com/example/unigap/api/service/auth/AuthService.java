package com.example.unigap.api.service.auth;


import com.example.unigap.api.dto.in.AuthLoginDtoIn;
import com.example.unigap.api.dto.out.AuthLoginDtoOut;

public interface AuthService {
  AuthLoginDtoOut login(AuthLoginDtoIn loginDtoIn);
}
