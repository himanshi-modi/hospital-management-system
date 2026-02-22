package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.enums.AuthProviderType;
import com.himanshi.hospital.entity.model.OAuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OAuthAccountRepository extends JpaRepository< OAuthAccount,Long> {

    Optional<OAuthAccount> findByProviderTypeAndProviderId(AuthProviderType providerType, String providerId);
}
