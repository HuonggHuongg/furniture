package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findOrderItemByOrderUser_OrderId(Integer orderId);
}
