package com.himanshi.hospital.controller;

import com.himanshi.hospital.service.PatientService;
import com.himanshi.hospital.dto.PatientResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<PatientResponseDto> getAllPatients(@RequestParam (value="page", defaultValue = "0") Integer PageNumber,
                                                             @RequestParam(value="size", defaultValue = "10")Integer pageSize){
        return ResponseEntity.ok(patientService.getAllPatients(PageNumber,pageSize));
    }
}
