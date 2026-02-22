package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}