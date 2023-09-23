package com.brotech.Auth.JWT;

import com.brotech.Auth.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class JWTUtilsTest {

    @InjectMocks
    private JWTUtils jwtUtil;
    @Mock
    private Authentication authentication;


    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        jwtUtil = new JWTUtils();
        // Access the private SECRET_KEY field using reflection
        Field secretKeyField = JWTUtils.class.getDeclaredField("SECRET_KEY");
        secretKeyField.setAccessible(true);
        secretKeyField.set(jwtUtil, "28wQQXXMs6Jl5LzDU7XWe7oxxfWowBWsb8EMhFFCbtEtneAff2YsmYlKMO5OcCKCsE4FB9r91tOQvFhuMcrZDQ==");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtil.generateToken(createAuthentication());
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void getUser() {
        String token = jwtUtil.generateToken(createAuthentication());
        String user = jwtUtil.getUser(token);
        assertEquals(user,"testUser");
    }


    private Authentication createAuthentication() {
        User user = new User("testUser", "testPassword");
        when(authentication.getPrincipal()).thenReturn(user);
        return authentication;
    }
}