package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderUserRepo extends JpaRepository<OrderUser, Integer> {
}
