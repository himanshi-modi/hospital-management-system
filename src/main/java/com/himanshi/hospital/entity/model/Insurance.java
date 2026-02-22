package com.himanshi.hospital.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50,unique = true)
    private String policyNumber;

    @Column(nullable = false , length = 100)
    private String provider;

    @Column
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDate createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;
}
