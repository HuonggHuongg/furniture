package com.graduation.furniture.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordDTO{
    private String email;
    private String resetPassword;
    private String token;
}
