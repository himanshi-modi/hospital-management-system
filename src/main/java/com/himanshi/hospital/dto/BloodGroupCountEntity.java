package com.himanshi.hospital.dto;

import com.himanshi.hospital.entity.enums.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountEntity {
    private BloodGroupType bloodGroupType;
    private Long id;

}
