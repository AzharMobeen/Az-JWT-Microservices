package com.az.jwt.example.client.service;

import com.az.jwt.example.client.model.AuthenticationRequest;
import com.az.jwt.example.client.model.AuthenticationResponse;

public interface AzJwtClientService {

    AuthenticationResponse authenticationApi(AuthenticationRequest request);
    String authorizationApi(String jwt);
}
