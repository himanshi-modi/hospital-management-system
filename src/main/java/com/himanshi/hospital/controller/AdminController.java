package com.himanshi.hospital.controller;

import com.himanshi.hospital.dto.DoctorRequestDto;
import com.himanshi.hospital.dto.DoctorResponseDto;
import com.himanshi.hospital.service.DoctorService;
import com.himanshi.hospital.service.PatientService;
import com.himanshi.hospital.dto.PatientResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")

public class AdminController {
    private final PatientService patientService;
    private final DoctorService doctorService;

    @PreAuthorize("hasAuthority('patient:read')")
    @GetMapping("/patients")
    public ResponseEntity<PatientResponseDto> getAllPatients(@RequestParam (value="page", defaultValue = "0") Integer PageNumber,
                                                             @RequestParam(value="size", defaultValue = "10")Integer pageSize){
        System.out.println("Controller called");
        return ResponseEntity.ok(patientService.getAllPatients(PageNumber,pageSize));
    }

    @PreAuthorize("hasAuthority('doctor:create')")
    @PostMapping("/onBoardNewDoctors")
    public ResponseEntity<DoctorResponseDto> onBoardDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctors(doctorRequestDto));
    }
}
