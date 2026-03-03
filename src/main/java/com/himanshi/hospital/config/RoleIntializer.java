package com.himanshi.hospital.config;

import com.himanshi.hospital.entity.model.Role;
import com.himanshi.hospital.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleIntializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("ROLE INITIALIZER RUNNING...");
        if(roleRepository.findByName("ROLE_ADMIN").isEmpty()){
            roleRepository.save(new Role(null,"ROLE_ADMIN"));
        }
        if(roleRepository.findByName("ROLE_DOCTOR").isEmpty()){
            roleRepository.save(new Role(null,"ROLE_DOCTOR"));
        }
        if(roleRepository.findByName("ROLE_PATIENT").isEmpty()){
            roleRepository.save(new Role(null,"ROLE_PATIENT"));
        }
    }
}
