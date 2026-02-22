package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}