package com.az.jwt.example.client.util;

import com.az.jwt.example.client.model.AuthenticationRequest;
import com.az.jwt.example.client.model.AuthenticationResponse;
import com.az.jwt.example.client.service.AzJwtClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AzJwtClientService service;

    @Override
    public void run(String... args) throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("user", "password");

        AuthenticationResponse response = service.authenticationApi(request);
        System.out.println("AuthenticationResponse: "+response);
        String output = service.authorizationApi(response.getJwt());
        System.out.println("AuthorizationResponse "+ output);
    }
}
