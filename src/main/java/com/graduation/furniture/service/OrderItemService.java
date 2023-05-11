package com.graduation.furniture.service;

import com.graduation.furniture.entities.OrderItem;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    void deleteById(Integer id);

    Optional<OrderItem> findById(Integer id);

    Page<OrderItem> findAll(int pageNum, int size);
}
