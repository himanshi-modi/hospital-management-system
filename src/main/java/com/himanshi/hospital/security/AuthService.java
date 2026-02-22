package com.himanshi.hospital.security;

import com.himanshi.hospital.dto.LoginRequestDto;
import com.himanshi.hospital.dto.LoginResponseDto;
import com.himanshi.hospital.dto.SignupRequestDto;
import com.himanshi.hospital.dto.SignupResponseDto;
import com.himanshi.hospital.entity.model.User;
import com.himanshi.hospital.repository.OAuthAccountRepository;
import com.himanshi.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final OAuthAccountRepository oauthAccountRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authenticationToken=new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        Authentication authenticationAuthenticated=authenticationManager.authenticate(authenticationToken);

        User user= (User) authenticationAuthenticated.getPrincipal();

        String jwt=authUtil.generateAccessToken(user);
        return new LoginResponseDto(jwt,user.getId());

    }

    public SignupResponseDto signUp(SignupRequestDto signupRequestDto) {
        if (userRepository.findByUsername(signupRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists!");
        }
        if (userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }
        User user=userRepository.save(User.builder()
                .email(signupRequestDto.getEmail())
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build());

        return modelMapper.map(user, SignupResponseDto.class);
    }


}
