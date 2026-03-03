package com.himanshi.hospital.controller;

import com.himanshi.hospital.service.AppointmentService;
import com.himanshi.hospital.dto.AppointmentResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/doctors")
@RestController
@AllArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasAuthority('appointment:read')")
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsofDoctors(){
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor());
    }

}
