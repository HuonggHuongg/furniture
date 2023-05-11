package com.graduation.furniture.dto;

import com.graduation.furniture.entities.Role;
import com.graduation.furniture.entities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRoleDTO {
    private  Integer id;

    private Users user;

    private Role role;
}
