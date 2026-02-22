package com.himanshi.hospital.service;

import com.himanshi.hospital.dto.DoctorResponseDto;
import com.himanshi.hospital.entity.model.Doctor;
import com.himanshi.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    public List<DoctorResponseDto> getAllDoctors() {
        List<Doctor> listOfDoctors=doctorRepository.findAll();
        List<DoctorResponseDto> list=listOfDoctors.stream().map(doctor->modelMapper.map(doctor,DoctorResponseDto.class)).toList();
        return list;

    }

}
