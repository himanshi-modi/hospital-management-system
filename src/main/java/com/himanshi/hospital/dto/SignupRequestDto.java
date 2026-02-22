package com.himanshi.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;




}
