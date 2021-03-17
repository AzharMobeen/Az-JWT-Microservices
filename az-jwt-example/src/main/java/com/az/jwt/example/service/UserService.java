package com.az.jwt.example.service;

import com.az.jwt.example.model.AuthenticationRequest;
import com.az.jwt.example.model.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AuthenticationResponse getJwtForAuthorizeUser(AuthenticationRequest request);
}
