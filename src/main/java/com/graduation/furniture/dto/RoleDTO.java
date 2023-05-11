package com.graduation.furniture.dto;

import com.graduation.furniture.entities.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Integer roleId;

    private String roleName;

    private Set<UserRole> userRoles;
}
