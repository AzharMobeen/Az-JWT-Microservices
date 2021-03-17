package com.az.jwt.example.controller;

import com.az.jwt.example.model.AuthenticationRequest;
import com.az.jwt.example.model.AuthenticationResponse;
import com.az.jwt.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("UserController Test Cases")
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("CheckAuthorization Test ")
    final void testCheckAuthorization() {
        String expectedResponse = "JWT Authorization working fine";
        String response = userController.checkAuthorization();
        assertEquals(response,expectedResponse);
    }

    @Test
    @DisplayName("AuthenticateUser Test")
    void testAuthenticateUser() {
        AuthenticationResponse response = new AuthenticationResponse("test");
        Mockito.when(userService.getJwtForAuthorizeUser(any())).thenReturn(response);
        ResponseEntity<AuthenticationResponse> authenticationResponse = userController.authenticateUser(new AuthenticationRequest());
        assertEquals(HttpStatus.OK,authenticationResponse.getStatusCode());
    }
}