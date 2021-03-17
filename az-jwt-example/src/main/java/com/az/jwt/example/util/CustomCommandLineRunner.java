package com.az.jwt.example.util;

import com.az.jwt.example.model.User;
import com.az.jwt.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setActive(true);
        user.setUserName("Azhar");
        user.setPassword("$2a$10$bqtsZH3eWquOt0fxfJ4DgOzxKqNGKZexcj3eDNiWxBUvFgHEEsXK.");
        user.setRoles("USER");
        User secondUser = new User();
        secondUser.setActive(true);
        secondUser.setUserName("user");
        secondUser.setPassword("$2a$10$bqtsZH3eWquOt0fxfJ4DgOzxKqNGKZexcj3eDNiWxBUvFgHEEsXK.");
        secondUser.setRoles("ADMIN");

        userRepository.saveAll(List.of(user,secondUser));

        log.debug("user successfully inserted ");
    }
}
