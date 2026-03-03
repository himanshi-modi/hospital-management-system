package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {




    Optional <Role> findByName(String roleAdmin);
}
