package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    List<CartItem> findCartItemByCart_User_UserName(String userName);

//    @Query(value = "from CartItem i join Cart c on c.cartId = i.cart.cartId join Users u on c.user.userName = u.userName where u.userName = :username")
//    List<CartItem> findCartItemsByUserName(@Param("username") String userName);
//    List<CartItem> findCartItemsByCart_UserUserNameAndProduct(String cart_user_userName);

    void deleteByProduct_ProductId(Integer productId);

    void deleteByCart_User_UserName(String userName);
}
