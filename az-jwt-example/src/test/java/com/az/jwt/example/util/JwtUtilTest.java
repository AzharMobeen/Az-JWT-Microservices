package com.az.jwt.example.util;

import com.az.jwt.example.TestUtil;
import com.az.jwt.example.exception.CustomRuntimeException;
import io.jsonwebtoken.ExpiredJwtException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtUtil Test Cases")
class JwtUtilTest {

    @InjectMocks
    JwtUtil jwtUtil;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("GenerateToken Test")
    void testGenerateToken() {
        UserDetails userDetails = TestUtil.buildUserDetails();
        String jwt = jwtUtil.generateToken(userDetails);
        assertTrue(StringUtils.isNotBlank(jwt));
    }

    @Nested
    @DisplayName("validatToken Test Cases")
    class ValidatToken {
        @Test
        @DisplayName("ValidateToken Success Scenario Test")
        void testValidateTokenSuccess() {
            String jwt = TestUtil.buildJwtTocken(false);
            Assertions.assertThatCode(() -> jwtUtil.validateTokenExpiration(jwt)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("ValidateToken failed Scenario Test with expired token")
        void testValidateTokenFailed() {
            String jwt = TestUtil.buildJwtTocken(true);
            assertThrows(ExpiredJwtException.class, ()-> jwtUtil.validateTokenExpiration(jwt), "JWT is expired");
        }
    }
}