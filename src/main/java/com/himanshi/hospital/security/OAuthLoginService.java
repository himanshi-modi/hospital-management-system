package com.himanshi.hospital.security;

import com.himanshi.hospital.dto.LoginResponseDto;
import com.himanshi.hospital.entity.enums.AuthProviderType;
import com.himanshi.hospital.entity.model.OAuthAccount;
import com.himanshi.hospital.entity.model.User;
import com.himanshi.hospital.repository.OAuthAccountRepository;
import com.himanshi.hospital.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OAuthLoginService {
    private final UserRepository userRepository;
    private final OAuthAccountRepository oauthAccountRepository;
    private final AuthUtil authUtil;


    @Transactional
    public ResponseEntity<LoginResponseDto> handleLoginRequest(AuthProviderType authProviderType, String providerId, OAuth2User oAuth2User, String registrationId) {

        // Step 1: Check if OAuthAccount exists
        Optional<OAuthAccount> oauthAccountOptional =
                oauthAccountRepository.findByProviderTypeAndProviderId(authProviderType, providerId);

        User user;

        if (oauthAccountOptional.isPresent()) {
            // Already linked → login
            user = oauthAccountOptional.get().getUser();
        } else {
            // Step 2: Check if any user exists with same email
            String email = oAuth2User.getAttribute("email");
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email not provided by OAuth2 provider.");
            }

            Optional<User> locallyLoggedInUser = userRepository.findByEmail(email);

            if (locallyLoggedInUser.isPresent()) {
                // Email exists → cannot signup, throw exception
                throw new BadCredentialsException(
                        "This email already exists. Please login with your existing provider: " + email
                );
            }

            // Step 3: No user exists → signup
            String username = authUtil.getUsernameForAuthUser(authProviderType, oAuth2User);
            User newUser = User.builder()
                    .email(email)
                    .username(username)
                    .password(null) // OAuth users don't need password
                    .build();

            user = userRepository.save(newUser);



            OAuthAccount oauthAccount = new OAuthAccount();
            oauthAccount.setProviderType(authProviderType);
            oauthAccount.setProviderId(providerId);
            oauthAccount.setUser(user);

            oauthAccountRepository.save(oauthAccount);
        }

        // Step 4: Generate JWT
        String jwt = authUtil.generateAccessToken(user); // pass user to generate token

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setJwt_token(jwt);
        loginResponseDto.setUserId(user.getId());

        return ResponseEntity.ok(loginResponseDto);
    }
}
