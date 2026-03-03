package com.himanshi.hospital.config;

import com.himanshi.hospital.entity.model.Role;
import com.himanshi.hospital.entity.model.User;
import com.himanshi.hospital.repository.RoleRepository;
import com.himanshi.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Check if admin already exists
        if (userRepository.findByEmail("admin@hospital.com").isPresent()) {
            return;
        }

        // Fetch ROLE_ADMIN
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        // Create admin user
        User admin = User.builder()
                .username("admin")
                .email("admin@hospital.com")
                .password(passwordEncoder.encode("admin123"))
                .build();

        admin.getRoles().add(adminRole);

        userRepository.save(admin);

        System.out.println("Admin user created successfully!");
    }
}
