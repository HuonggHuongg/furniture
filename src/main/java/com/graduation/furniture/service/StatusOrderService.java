package com.graduation.furniture.service;

import com.graduation.furniture.entities.OrderUser;

import java.util.Optional;

public interface StatusOrderService {
    Optional<OrderUser> findById(Integer id);
}
