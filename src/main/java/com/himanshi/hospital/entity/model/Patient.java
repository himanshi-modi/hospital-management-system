package com.himanshi.hospital.entity.model;

import com.himanshi.hospital.entity.enums.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Long id;

    @Column(nullable = false ,length = 40)
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    private String gender;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne
    @MapsId
    @JoinColumn(name="id", nullable = false, unique = true)
    private User user;

    @OneToOne(cascade={CascadeType.ALL},orphanRemoval = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient" , cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch=FetchType.EAGER)

    private List<Appointment> appointments=new ArrayList<>();

}
