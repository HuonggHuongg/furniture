package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderUserRepo extends JpaRepository<OrderUser, Integer> {

    List<OrderUser> findOrderUserByStatusOrder_StatusId(Integer statusId);

    List<OrderUser> findOrderUserByUserUserName(String userName);
}
