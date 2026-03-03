package com.himanshi.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String reason;

}
