package com.himanshi.hospital.entity.model;

import com.himanshi.hospital.entity.enums.AuthProviderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "oauth_accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"provider_type", "providerId"})
        }
)
public class OAuthAccount {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProviderType providerType;

    @Column(nullable = false)
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


}
