package com.graduation.furniture.service;

import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Feedback;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    Feedback update(Feedback feedback);

    void deleteById(Integer id);

    Optional<Feedback> findById(Integer id);

    Page<Feedback> findAll(int pageNum, int size);

    List<Feedback> findByProduct_ProductIdAndUser_UserName(Integer productId, String userName);

    List<Feedback> findByProduct_ProductId(Integer productId);
}
