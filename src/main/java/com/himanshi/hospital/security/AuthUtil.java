package com.himanshi.hospital.security;

import com.himanshi.hospital.entity.enums.AuthProviderType;
import com.himanshi.hospital.entity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import static com.himanshi.hospital.entity.enums.AuthProviderType.GITHUB;
import static com.himanshi.hospital.entity.enums.AuthProviderType.GOOGLE;

@Component
public class AuthUtil {
    //from application.properties
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    //converting the string jwtSecretKey to bytes using hmac-sha-algorithm
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    //creating token(header+paylod+signature)
    public String generateAccessToken(User user){

        return Jwts.builder() //the library is creating header

                //payload info
                .subject(user.getUsername())
                .claim("userId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))

                //signature created using secretKey
                .signWith(getSecretKey())

                //joining header , payload and signature
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claim=Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claim.getSubject();
    }

    public AuthProviderType getProviderType(String registrationId) {
        return switch (registrationId.toLowerCase()){
            case "google"-> GOOGLE;
            case "github"-> GITHUB;
            default -> throw new IllegalArgumentException("Unsupported OAuth2Provider : "+registrationId);

        };
    }


    public String getProviderId(String registrationId, OAuth2User oAuth2User) {
        System.out.println("ATTRIBUTES: " + oAuth2User.getAttributes());
        Object id = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id");
            default -> throw new IllegalArgumentException(
                    "Unsupported oAuth2 provider: " + registrationId);
        };

        if (id == null) {
            throw new IllegalArgumentException(
                    "Unable to determine providerId for oAuth2 login");
        }

        return String.valueOf(id);
    }

    public String getUsernameForAuthUser(AuthProviderType authProviderType, OAuth2User oAuth2User) {

        String base = switch (authProviderType) {
            case GOOGLE -> oAuth2User.getAttribute("given_name"); // or "name"
            case GITHUB -> oAuth2User.getAttribute("login");
            default -> "user";
        };

        // fallback if attribute is missing
        if (base == null || base.isBlank()) {
            base = "user";
        }

        String username = base.toLowerCase().replaceAll("\\s+", "")
                + "_" + UUID.randomUUID().toString().substring(0, 8);
        return username;

    }
}
