package com.himanshi.hospital.service;

import com.himanshi.hospital.dto.DoctorRequestDto;
import com.himanshi.hospital.dto.DoctorResponseDto;
import com.himanshi.hospital.entity.model.Doctor;
import com.himanshi.hospital.entity.model.Role;
import com.himanshi.hospital.entity.model.User;
import com.himanshi.hospital.repository.DoctorRepository;
import com.himanshi.hospital.repository.RoleRepository;
import com.himanshi.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public List<DoctorResponseDto> getAllDoctors() {
        List<Doctor> listOfDoctors=doctorRepository.findAll();
        List<DoctorResponseDto> list=listOfDoctors.stream().map(doctor->modelMapper.map(doctor,DoctorResponseDto.class)).toList();
        return list;

    }

    public DoctorResponseDto onBoardNewDoctors(DoctorRequestDto doctorRequestDto) {
        if (userRepository.findByEmail(doctorRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists with this email!");
        }

        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .username(doctorRequestDto.getUsername())
                .email(doctorRequestDto.getEmail())
                .password(passwordEncoder.encode(doctorRequestDto.getPassword()))
                .build();

        user.getRoles().add(doctorRole);

        userRepository.save(user);


        Doctor doctor = Doctor.builder()
                .name(doctorRequestDto.getName())
                .specialization(doctorRequestDto.getSpecialization())
                .user(user)
                .build();

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
