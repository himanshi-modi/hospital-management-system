package com.himanshi.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    List<PatientDto> patients;
    private int page;
    private int size;
    private Long totalPatients;
}
