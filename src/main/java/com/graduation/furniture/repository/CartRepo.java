package com.graduation.furniture.repository;

import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findByUser_UserName(String userName);

    @Query("select sum(ci.paymentCartItem) as total from CartItem ci where ci.cart.cartId = :cartId group by ci.cart.cartId")
    Integer getTotal(@Param("cartId") Integer cartId);
}
