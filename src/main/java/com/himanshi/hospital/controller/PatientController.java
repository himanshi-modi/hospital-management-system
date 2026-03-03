package com.himanshi.hospital.controller;

import com.himanshi.hospital.dto.AppointmentRequestDto;
import com.himanshi.hospital.dto.AppointmentResponseDto;

import com.himanshi.hospital.dto.PatientResponseDto;
import com.himanshi.hospital.service.AppointmentService;
import com.himanshi.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {


    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @PreAuthorize("hasAuthority('appointment:create')")
    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment( @RequestBody AppointmentRequestDto appointmentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(appointmentRequestDto));

    }

    @PreAuthorize("hasAuthority('patient:read:self')")
    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile(){
        return ResponseEntity.ok(
                patientService.getCurrentPatientProfile()
        );
    }
}
