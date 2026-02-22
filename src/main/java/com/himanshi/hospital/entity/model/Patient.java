package com.himanshi.hospital.entity.model;

import com.himanshi.hospital.entity.enums.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(

        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_patient_name_birthdate",
                        columnNames = {"name", "birthdate"}
                )
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthdate")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,length = 40)
    private String name;

    @Column(unique = true,nullable = false )
    private String email;

    private LocalDate birthdate;

    private String gender;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade={CascadeType.ALL},orphanRemoval = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient" , cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch=FetchType.EAGER)

    private List<Appointment> appointments=new ArrayList<>();

}
