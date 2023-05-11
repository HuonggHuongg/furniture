package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOrderRepo extends JpaRepository<StatusOrder, Integer> {
}
