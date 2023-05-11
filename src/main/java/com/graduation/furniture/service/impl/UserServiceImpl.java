package com.graduation.furniture.service.impl;

import com.graduation.furniture.dto.RegisterUserDTO;
import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.Role;
import com.graduation.furniture.entities.UserRole;
import com.graduation.furniture.entities.Users;
import com.graduation.furniture.repository.CartRepo;
import com.graduation.furniture.repository.RoleRepo;
import com.graduation.furniture.repository.UserRepo;
import com.graduation.furniture.repository.UserRoleRepo;
import com.graduation.furniture.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    private static final boolean DELETED_FALSE = false;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(RegisterUserDTO registerUserDTO) {
        LocalDate now = LocalDate.now();
        Users user = new Users();
        BeanUtils.copyProperties(registerUserDTO, user);
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setCreatedAt(now);
        Users newUser = userRepo.save(user);
        Cart cart = new Cart();
        cart.setUser(newUser);
        cart.setCreatedAt(now);
        cartRepo.save(cart);
        return newUser;
    }

    @Override
    public Users save(Users user) {
        LocalDate now = LocalDate.now();
        user.setCreatedAt(now);
        Users newUser = userRepo.save(user);
        Cart cart = new Cart();
        cart.setUser(newUser);
        cart.setCreatedAt(now);
        cartRepo.save(cart);
        return newUser;
    }

    @Override
    public Users update(Users user) {
        LocalDateTime now = LocalDateTime.now();
        user.setUpdatedAt(now);
        return userRepo.save(user);
    }

    @Override
    public void deleteById(String userName) {
        Users user = userRepo.findById(userName).orElse(null);
        if (user != null) {
            user.setDeleted(true);
            userRepo.save(user);
        }
    }

    @Override
    public Optional<Users> findById(String userName) {
        return userRepo.findById(userName);
    }

    @Override
    public Page<Users> findAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum - 1, size);

        return userRepo.findAllByDeleted(DELETED_FALSE, pageable);
    }
}
