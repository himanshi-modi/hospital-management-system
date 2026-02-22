package com.himanshi.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.EmbeddedTable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    String jwt_token;
    Long userId;
}
