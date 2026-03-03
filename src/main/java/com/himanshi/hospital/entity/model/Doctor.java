package com.himanshi.hospital.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100,nullable = false)
    private String specialization;


    @OneToOne
    @MapsId
    @JoinColumn(name="id", nullable = false, unique = true)
    private User user;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> departments=new HashSet<>();

    @OneToMany(mappedBy = "doctor" , cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    private List<Appointment> appointments=new ArrayList<>();


}
