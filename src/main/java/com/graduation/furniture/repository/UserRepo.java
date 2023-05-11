package com.graduation.furniture.repository;

import com.graduation.furniture.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, String> {

    Page<Users> findAllByDeleted(boolean deleted, Pageable pageable);
}
