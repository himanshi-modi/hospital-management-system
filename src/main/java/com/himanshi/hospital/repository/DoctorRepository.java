package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.model.Doctor;
import com.himanshi.hospital.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUser(User user);
}