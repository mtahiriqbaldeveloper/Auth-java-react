package com.brotech.Auth.controller;


import com.brotech.Auth.entity.Account;
import com.brotech.Auth.expection.ErrorMessage;
import com.brotech.Auth.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@Controller
@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/auth/")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private final AccountService accountService;


    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Account account){
        if(!accountService.isAccountAlreadyPresent(account)) {
            accountService.saveAccount(account);
            log.info("user created "+account);
            return new ResponseEntity<>("account created", HttpStatus.CREATED);
        }else {
            log.info(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                    new Date(),"user already exists","email should be unique"),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
