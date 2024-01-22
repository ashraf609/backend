package com.dev.springboot.controller;


import com.dev.springboot.dto.SingupRequest;
import com.dev.springboot.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singup")
public class SingUpController {

    private final AuthService authService;


    public SingUpController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<String> singupAdmin(@RequestBody SingupRequest singupRequest)
    {
            boolean isUserCreated = authService.createAdmin(singupRequest);
            if(isUserCreated)
            {
                return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create User");
            }
    }
}
