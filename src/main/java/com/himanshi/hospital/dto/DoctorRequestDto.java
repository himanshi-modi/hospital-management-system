package com.himanshi.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private String username;
    private String email;
    private String password;

    private String name;
    private String specialization;
}
