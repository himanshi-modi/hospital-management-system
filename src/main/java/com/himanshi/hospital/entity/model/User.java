package com.himanshi.hospital.entity.model;

import com.himanshi.hospital.security.AppRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Doctor doctor;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles=new HashSet<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<OAuthAccount> authAccounts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles) {

            // Add ROLE_XXX authority
            authorities.add(new SimpleGrantedAuthority(role.getName()));

            // Convert DB role name → AppRole enum
            AppRole appRole = AppRole.valueOf(role.getName().replace("ROLE_", ""));

            // Add permissions of that role
            appRole.getPermissions().forEach(permission ->
                    authorities.add(new SimpleGrantedAuthority(permission.getPermission()))
            );
        }

        return authorities;
    }
}
