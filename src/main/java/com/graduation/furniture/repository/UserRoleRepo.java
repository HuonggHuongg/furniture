package com.graduation.furniture.repository;

import com.graduation.furniture.entities.Role;
import com.graduation.furniture.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByRole(Role role);

    List<UserRole> findByUser_UserName(String username);
}
