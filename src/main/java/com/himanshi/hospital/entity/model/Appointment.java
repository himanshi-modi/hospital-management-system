package com.himanshi.hospital.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length=500)
    private String reason;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Patient patient;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Doctor doctor;

}
