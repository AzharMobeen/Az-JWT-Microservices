package com.az.jwt.example.service.impl;

import com.az.jwt.example.model.AuthenticationRequest;
import com.az.jwt.example.model.AuthenticationResponse;
import com.az.jwt.example.model.MyUserDetails;
import com.az.jwt.example.model.User;
import com.az.jwt.example.repository.UserRepository;
import com.az.jwt.example.service.UserService;
import com.az.jwt.example.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthenticationResponse getJwtForAuthorizeUser(AuthenticationRequest request) {
        log.debug("getJwtForAuthorizeUser method called!");
        validateUser(request);
        final UserDetails userDetails = loadUserByUsername(request.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.debug("loadUserByUsername method called! [{}]", username);
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow( () -> new UsernameNotFoundException("Bad Credentials"));
        log.debug("UserName is belongs to valid user {},{}", username, user.get());
        return user.map(MyUserDetails::new).get();
    }

    private void validateUser(AuthenticationRequest request){

        log.debug("validateUser methed called with request [{}]",request);
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUserName(),
                request.getPassword()));
        log.debug("User Successfully Authenticated!");
    }
}
