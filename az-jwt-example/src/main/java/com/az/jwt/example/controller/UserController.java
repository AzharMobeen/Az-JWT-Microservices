package com.az.jwt.example.controller;

import com.az.jwt.example.model.AuthenticationRequest;
import com.az.jwt.example.model.AuthenticationResponse;
import com.az.jwt.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String checkAuthorization() {
        log.debug("checkAuthorization methed called with request");
        return "JWT Authorization working fine";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
        log.debug("authenticateUser methed called with request [{}]",request);
        AuthenticationResponse response = userService.getJwtForAuthorizeUser(request);
        return ResponseEntity.ok(response);
    }
}
