package com.himanshi.hospital.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentResponseDto {
    private Long appointmentId;
    private LocalDateTime appointmentTime;
    private String reason;
    private String patientName;


}
