package com.himanshi.hospital.service;

import com.himanshi.hospital.dto.PatientDto;
import com.himanshi.hospital.dto.PatientResponseDto;
import com.himanshi.hospital.entity.model.Patient;
import com.himanshi.hospital.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Patient getPatient(Long id){
        Patient p1=patientRepository.findById(id).orElseThrow();

        return p1;
    }
    @Transactional
    public PatientResponseDto getAllPatients(int pageNumber , int pageSize){
       Pageable pageable= PageRequest.of(pageNumber, pageSize);
       Page<Patient> patientPage=patientRepository.findAll(pageable);

        List<PatientDto> patientsDto = patientPage.getContent()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .toList();
        PatientResponseDto patientResponseDto=new PatientResponseDto();
        patientResponseDto.setPatients(patientsDto);
        patientResponseDto.setPage(patientPage.getNumber());
        patientResponseDto.setSize(patientPage.getSize());
        patientResponseDto.setTotalPatients(patientPage.getTotalElements());
        return patientResponseDto;
    }
}
