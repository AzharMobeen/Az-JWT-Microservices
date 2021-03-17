package com.az.jwt.example.service.impl;

import com.az.jwt.example.model.AuthenticationRequest;
import com.az.jwt.example.model.AuthenticationResponse;
import com.az.jwt.example.model.MyUserDetails;
import com.az.jwt.example.model.User;
import com.az.jwt.example.repository.UserRepository;
import com.az.jwt.example.util.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@DisplayName("UserServiceImpl Test Cases")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void beforeAll(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetJwtForAuthorizeUser() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("","");
        Mockito.when(authenticationManager.authenticate(token)).thenReturn(token);
        Optional<User> user = Optional.of(new User());
        Mockito.when(userRepository.findByUserName(any())).thenReturn(user);
        Mockito.when(jwtUtil.generateToken(any())).thenReturn("token");
        AuthenticationRequest request = new AuthenticationRequest();
        AuthenticationResponse response = userService.getJwtForAuthorizeUser(new AuthenticationRequest());
        assertNotNull(response.getJwt());
    }

    @Test
    void testLoadUserByUsername() {
        Optional<User> user = Optional.empty();
        Mockito.when(userRepository.findByUserName(any())).thenReturn(user);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(any(String.class)),
                "Bad Credentials");
    }
}