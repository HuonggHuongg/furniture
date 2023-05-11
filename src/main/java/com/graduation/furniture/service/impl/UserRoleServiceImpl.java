package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Role;
import com.graduation.furniture.entities.UserRole;
import com.graduation.furniture.entities.Users;
import com.graduation.furniture.repository.RoleRepo;
import com.graduation.furniture.repository.UserRoleRepo;
import com.graduation.furniture.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserRole save(Users user) {
        Role role = roleRepo.findById(2).orElse(null);
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        return userRoleRepo.save(userRole);
    }

    @Override
    public List<UserRole> findUserRoleByUsername(String username) {
        return userRoleRepo.findByUser_UserName(username);
    }
}
