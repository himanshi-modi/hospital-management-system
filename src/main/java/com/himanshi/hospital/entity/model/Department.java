package com.himanshi.hospital.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 100)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false)
    private Doctor doctorHead;

    @ManyToMany
    private Set<Doctor> doctors=new HashSet<>();




}
