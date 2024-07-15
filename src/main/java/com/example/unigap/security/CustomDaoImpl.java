package com.example.unigap.security;

import com.example.unigap.common.code.ErrorCode;
import com.example.unigap.common.exception.ApiException;
import com.example.unigap.api.entity.User;
import com.example.unigap.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomDaoImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
      throw new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "username not found");
    }
    return userEntity;
  }
}
