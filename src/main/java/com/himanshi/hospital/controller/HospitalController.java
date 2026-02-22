package com.himanshi.hospital.controller;

import com.himanshi.hospital.service.DoctorService;
import com.himanshi.hospital.dto.DoctorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class HospitalController {

    private final DoctorService doctorService;
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>>getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
