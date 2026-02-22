package com.himanshi.hospital.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.himanshi.hospital.dto.LoginResponseDto;
import com.himanshi.hospital.entity.enums.AuthProviderType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final AuthUtil authUtil;
   private final OAuthLoginService oAuthLoginService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String registrationId = token.getAuthorizedClientRegistrationId();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        AuthProviderType authProviderType=authUtil.getProviderType(registrationId);
        String providerId=authUtil.getProviderId(registrationId,oAuth2User);

        ResponseEntity<LoginResponseDto> loginResponseDto=oAuthLoginService.handleLoginRequest(authProviderType,providerId,oAuth2User,registrationId);
        response.setStatus(loginResponseDto.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(loginResponseDto.getBody()));


    }



}
