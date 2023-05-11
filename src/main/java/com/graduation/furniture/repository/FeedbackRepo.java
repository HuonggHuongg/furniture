package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByProduct_ProductIdAndUser_UserName(Integer productId, String userName);

    List<Feedback> findByProduct_ProductId(Integer productId);

}
