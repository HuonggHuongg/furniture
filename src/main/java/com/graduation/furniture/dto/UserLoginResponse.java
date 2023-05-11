package com.graduation.furniture.dto;

import com.graduation.furniture.entities.UserRole;
import com.graduation.furniture.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    private String jWT;

    private Users user;

    private List<String> roles;
}
