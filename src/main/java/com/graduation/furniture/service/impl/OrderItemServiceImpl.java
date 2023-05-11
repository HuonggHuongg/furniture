package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.entities.OrderUser;
import com.graduation.furniture.repository.OrderItemRepo;
import com.graduation.furniture.service.OrderItemService;
import com.graduation.furniture.service.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Override
    public OrderItem save(OrderItem orderItem) {
        LocalDate now = LocalDate.now();
        orderItem.setCreatedAt(now);
        return orderItemRepo.save(orderItem);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Optional<OrderItem> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Page<OrderItem> findAll(int pageNum, int size) {
        return null;
    }
}