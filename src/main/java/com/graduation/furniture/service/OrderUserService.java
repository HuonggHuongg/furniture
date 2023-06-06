package com.graduation.furniture.service;

import com.graduation.furniture.entities.OrderUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrderUserService {
    OrderUser save(OrderUser orderUser);

    OrderUser changeStatusOrder(OrderUser orderUser);

    OrderUser changeTotalOrder(OrderUser orderUser);

    OrderUser changePaymentStatus(Integer id);

    void deleteById(Integer id);

    Optional<OrderUser> findById(Integer id);

    Page<OrderUser> findAll(int pageNum, int size);

    List<OrderUser> findOrderUserByStatusPending();

    List<OrderUser> findOrderUserByStatusDelivered();

    List<OrderUser> findOrderUserByUserUserName(String userName);
}
