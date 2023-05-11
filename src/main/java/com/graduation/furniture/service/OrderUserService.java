package com.graduation.furniture.service;

import com.graduation.furniture.entities.OrderUser;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderUserService {
    OrderUser save(OrderUser orderUser);

    OrderUser changeStatusOrder(OrderUser orderUser);

    OrderUser changeTotalOrder(OrderUser orderUser);

    void deleteById(Integer id);

    Optional<OrderUser> findById(Integer id);

    Page<OrderUser> findAll(int pageNum, int size);
}
