package com.graduation.furniture.service;

import com.graduation.furniture.entities.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findById(Integer id);

    Page<OrderItem> findAll(int pageNum, int size);

    List<OrderItem> findOrderItemByOrderUserOrderId(Integer orderId);

    OrderItem changeFeedbackStatus(Integer orderItemId);
}
