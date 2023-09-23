package com.brotech.Auth.controller;



import com.brotech.Auth.JWT.JWTUtils;
import com.brotech.Auth.common.payload.Login;
import com.brotech.Auth.entity.Account;
import com.brotech.Auth.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/auth/")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;


    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Account account) {
        return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
    }
    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody @Valid Login login){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }

}
