package com.az.jwt.example;

import com.az.jwt.example.model.MyUserDetails;
import com.az.jwt.example.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    public static User buildUser(){
        User user = new User();
        String userName = "userName";
        user.setUserName(userName);
        user.setPassword("password");
        user.setActive(true);
        return user;
    }

    public static UserDetails buildUserDetails(){
        return new MyUserDetails(buildUser());
    }

    public static String buildJwtTocken(boolean isExpireToken){
        Map<String, Object> claims = new HashMap<>();
        UserDetails userDetails = buildUserDetails();
        Date expireationDate = isExpireToken ? new Date(System.currentTimeMillis()) :
                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expireationDate)
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
    }
}
