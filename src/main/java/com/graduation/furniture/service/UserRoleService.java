package com.graduation.furniture.service;

import com.graduation.furniture.entities.Role;
import com.graduation.furniture.entities.UserRole;
import com.graduation.furniture.entities.Users;

import java.util.List;

public interface UserRoleService {

    UserRole save(Users user);

    List<UserRole> findUserRoleByUsername(String username);
}
