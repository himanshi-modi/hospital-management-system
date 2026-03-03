package com.himanshi.hospital.repository;

import com.himanshi.hospital.entity.model.BloodGroupCountEntity;
import com.himanshi.hospital.entity.model.Patient;
import com.himanshi.hospital.entity.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);


//    @Query("SELECT p FROM Patient p WHERE p.bloodGroup=?1")
//    List<Patient> findByBloodGroup( @Param("bloodGroup")BloodGroupType bloodGroup);

//    @Query("SELECT p.bloodGroup, count(p) FROM Patient p GROUP BY p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();

    @Query("SELECT new com.himanshi.hospital.entity.model.BloodGroupCountEntity(p.bloodGroup, COUNT(p)) " +
            "FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountEntity> countEachBloodGroupType();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name= :name WHERE p.id= :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

    @Query(value = "SELECT * FROM patient", nativeQuery=true)
    Page<Patient> getAllPatients(Pageable pageable);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    List<Patient> findAllPatientWithAppointment();



    Optional<Patient> findByUser(User user);
}

