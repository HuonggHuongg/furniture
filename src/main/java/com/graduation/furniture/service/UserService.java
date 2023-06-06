package com.graduation.furniture.service;

import com.graduation.furniture.dto.RegisterUserDTO;
import com.graduation.furniture.entities.Users;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    Users save(Users user);

    Users update(Users user);

    void deleteById(String userName);

    Optional<Users> findById(String userName);

    Page<Users> findAll(int pageNum, int size);

    Users registerUser(RegisterUserDTO registerUserDTO);

    Users findByEmail(String email);
}
