package com.himanshi.hospital.controller;

import com.himanshi.hospital.security.AuthService;
import com.himanshi.hospital.dto.LoginRequestDto;
import com.himanshi.hospital.dto.LoginResponseDto;
import com.himanshi.hospital.dto.SignupResponseDto;
import com.himanshi.hospital.dto.SignupRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping ("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto){
        System.out.println("SIGNUP HIT");
        return ResponseEntity.ok(authService.signUp(signupRequestDto));
    }

}
