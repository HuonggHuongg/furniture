package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.*;
import com.graduation.furniture.repository.OrderItemRepo;
import com.graduation.furniture.repository.OrderUserRepo;
import com.graduation.furniture.repository.StatusOrderRepo;
import com.graduation.furniture.service.CartItemService;
import com.graduation.furniture.service.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderUserServiceImpl implements OrderUserService {

    @Autowired
    private OrderUserRepo orderUserRepo;

    @Autowired
    private StatusOrderRepo statusOrderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Override
    public OrderUser save(OrderUser orderUser) {
        StatusOrder statusOrder = statusOrderRepo.findById(1).orElse(null);
        LocalDate now = LocalDate.now();
        orderUser.setCreatedAt(now);
        orderUser.setStatusOrder(statusOrder);
        return orderUserRepo.save(orderUser);
    }

    @Override
    public OrderUser changeStatusOrder(OrderUser orderUser) {
        StatusOrder statusOrder = statusOrderRepo.findById(2).orElse(null);
        LocalDateTime now = LocalDateTime.now();
        orderUser.setUpdatedAt(now);
        orderUser.setStatusOrder(statusOrder);
        return orderUserRepo.save(orderUser);
    }

    @Override
    public OrderUser changeTotalOrder(OrderUser orderUser) {
        Integer total = 0;
        List<OrderItem> orderItems = orderItemRepo.findOrderItemByOrderUser_OrderId(orderUser.getOrderId());
        for (OrderItem item: orderItems) {
            total += item.getPaymentOrderItem();
        }
        orderUser.setTotalOrder(total);
        return orderUserRepo.save(orderUser);
    }

    @Override
    public OrderUser changePaymentStatus(Integer id) {
        LocalDateTime updatedAt = LocalDateTime.now();
        OrderUser orderUser = orderUserRepo.findById(id).orElse(null);
        if (orderUser != null) {
//            orderUser.setCreatedAt(orderUser.getCreatedAt());
            orderUser.setPaymentStatus(true);
            orderUser.setUpdatedAt(updatedAt);
            orderUserRepo.save(orderUser);
        }
        return orderUser;
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public Optional<OrderUser> findById(Integer id) {
        return orderUserRepo.findById(id);
    }

    @Override
    public Page<OrderUser> findAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum-1, size);
        return orderUserRepo.findAll(pageable);
    }

    @Override
    public List<OrderUser> findOrderUserByStatusPending() {
        return orderUserRepo.findOrderUserByStatusOrder_StatusId(1);
    }

    @Override
    public List<OrderUser> findOrderUserByStatusDelivered() {
        return orderUserRepo.findOrderUserByStatusOrder_StatusId(2);
    }

    @Override
    public List<OrderUser> findOrderUserByUserUserName(String userName) {
        return orderUserRepo.findOrderUserByUserUserName(userName);
    }
}
