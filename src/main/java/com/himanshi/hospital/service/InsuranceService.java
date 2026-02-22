package com.himanshi.hospital.service;

import com.himanshi.hospital.entity.model.Insurance;
import com.himanshi.hospital.entity.model.Patient;
import com.himanshi.hospital.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsauranceToPatient(Insurance insurance , Long id){
        Patient patient=patientRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found with Id: "+id));
        patient.setInsurance(insurance);
        return patient;
    }
    @Transactional
    public Patient disAssociateInsuranceFromPatient(Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null);
        return patient;
    }
}
