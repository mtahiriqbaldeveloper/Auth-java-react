package com.brotech.Auth.JWT;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getToken(request);

            if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
                String user = jwtUtils.getUser(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, "");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }



    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token.startsWith("Bearer ") && StringUtils.hasText(token)) {
            System.out.println(token);
            String substring = token.substring(7);
            System.out.println(substring);
            return substring;
        }
        return null;
    }
}
