package com.example.unigap.api.controller;


import com.example.unigap.api.dto.in.AuthLoginDtoIn;
import com.example.unigap.api.dto.out.AuthLoginDtoOut;
import com.example.unigap.api.service.auth.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Login")
@RestController
@RequestMapping("/auth")

public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthLoginDtoOut> login(@RequestBody @Valid AuthLoginDtoIn loginDtoIn) {
    return new ResponseEntity<>(
      this.authService.login(loginDtoIn), HttpStatus.OK
    );
  }
}


