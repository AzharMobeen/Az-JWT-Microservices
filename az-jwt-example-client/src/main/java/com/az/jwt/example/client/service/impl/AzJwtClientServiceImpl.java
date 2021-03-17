package com.az.jwt.example.client.service.impl;

import com.az.jwt.example.client.model.AuthenticationRequest;
import com.az.jwt.example.client.model.AuthenticationResponse;
import com.az.jwt.example.client.service.AzJwtClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AzJwtClientServiceImpl implements AzJwtClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    private final String baseUrl = "http://localhost:7171";

    @Override
    public AuthenticationResponse authenticationApi(AuthenticationRequest request) {

        AuthenticationResponse response =  restTemplate.postForObject(baseUrl+"/authenticate", request,
                AuthenticationResponse.class);
        return response;
    }

    @Override
    public String authorizationApi(String jwt) {
        httpHeaders.add("Authorization", "Bearer "+jwt);
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(baseUrl + "/hello", HttpMethod.GET, entity, String.class);
        return exchange.getBody();
    }
}
