package com.himanshi.hospital.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false,length = 100,unique=true)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> departments=new HashSet<>();

    @OneToMany(mappedBy = "doctor" , cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    private List<Appointment> appointments=new ArrayList<>();


}
