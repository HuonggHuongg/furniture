package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.entities.OrderUser;
import com.graduation.furniture.service.OrderItemService;
import com.graduation.furniture.service.StatusOrderService;
import org.springframework.data.domain.Page;

import java.util.Optional;

public class StatusOrderServiceImpl implements StatusOrderService {

    @Override
    public Optional<OrderUser> findById(Integer id) {
        return Optional.empty();
    }
}
