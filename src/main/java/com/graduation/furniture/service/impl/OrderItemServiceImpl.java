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
import java.time.LocalDateTime;
import java.util.List;
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
    public Optional<OrderItem> findById(Integer id) {
        return orderItemRepo.findById(id);
    }

    @Override
    public Page<OrderItem> findAll(int pageNum, int size) {
        return null;
    }

    @Override
    public List<OrderItem> findOrderItemByOrderUserOrderId(Integer orderId) {
        return orderItemRepo.findOrderItemByOrderUser_OrderId(orderId);
    }

    @Override
    public OrderItem changeFeedbackStatus(Integer orderItemId) {
        LocalDateTime updatedAt = LocalDateTime.now();
        OrderItem orderItem = orderItemRepo.findById(orderItemId).orElse(null);
        if (orderItem != null) {
            orderItem.setFeedbackStatus(true);
            orderItem.setUpdatedAt(updatedAt);
            orderItemRepo.save(orderItem);
        }
        return orderItem;
    }


}
